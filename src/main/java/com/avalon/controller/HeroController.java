package com.avalon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avalon.domain.Hero;
import com.avalon.domain.User;
import com.avalon.service.HeroService;
import com.avalon.service.UserService;
import com.avalon.util.PageDao;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("hero")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @Autowired
    private UserService userService;
    
    @RequestMapping("queryAll")
    public String queryAll(HttpServletRequest request, Model model) {
        
        PageDao page = new PageDao(request);
        int currentPage = page.getCurrentPage();
        int pageSize = page.getPagesize();
        
        Page<Hero> list = (Page<Hero>) heroService.queryAll(currentPage, pageSize);
        
        long rsCount = list.getTotal();
        page.setRscount((int) rsCount);
        String pageTools = page.pagetool(PageDao.BbsText);
        
        model.addAttribute("pageTools", pageTools);
        model.addAttribute("heroList", list);
        return "/WEB-INF/views/dataList/heroList.jsp";
    }
    
    @RequestMapping("queryLike")
    public String queryLike(String heroName, Model model, HttpServletRequest request) {
        
        PageDao page = new PageDao(request);
        int currentPage = page.getCurrentPage();
        int pageSize = page.getPagesize();
        
        Page<Hero> list = null;
        if (heroName != null && !heroName.trim().equals("")) {
            list = (Page<Hero>) heroService.queryLike(heroName, currentPage, pageSize);
        } else {
            list = (Page<Hero>) heroService.queryAll(currentPage, pageSize);
        }
        
        long rsCount = list.getTotal();
        page.setRscount((int)rsCount);
        String pageTools = page.pagetool(PageDao.BbsText);
        
        model.addAttribute("pageTools", pageTools);
        model.addAttribute("heroList", list);
        return "/WEB-INF/views/dataList/heroList.jsp";
    }
    
    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id")Integer heroId, Model model, HttpServletRequest request) {
        
        Hero hero = heroService.queryById(heroId);
        
        String userid = (String) request.getSession().getAttribute("id");
        User user = userService.queryById(userid);
        
        model.addAttribute("user", user);
        model.addAttribute("hero", hero);
        
        return "/WEB-INF/views/detail/detailHero.jsp";
    }
    
    @GetMapping("collect/{id}")
    @ResponseBody
    public String collect(@PathVariable("id") String heroId, HttpSession session) {
        
        String userId = (String) session.getAttribute("id");
        System.out.println("成功收藏英雄，编号：" + heroId);
        User user = userService.queryById(userId);
        String collection = user.getHeroCollection();
        collection = collection + heroId + ",";
        user.setHeroCollection(collection);
        userService.updateCollection(user);
        return collection;
    }
    
    @GetMapping("uncollect/{id}")
    @ResponseBody
    public String uncollect(@PathVariable("id") String heroId, HttpSession session) {
        
        String userId = (String) session.getAttribute("id");
        System.out.println("取消收藏英雄，编号：" + heroId);
        User user = userService.queryById(userId);
        String collection = user.getHeroCollection();
        String[] collectList = collection.split(",");
        String newCollect = "";
        for (int j = 0; j < collectList.length; j++) {
            if (!collectList[j].equals(heroId)) {
                newCollect += collectList[j] + ",";
            }
        }
        user.setHeroCollection(newCollect);
        userService.updateCollection(user);
        return newCollect;
    }
}
