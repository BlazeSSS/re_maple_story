package com.avalon.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avalon.domain.Hero;
import com.avalon.domain.User;
import com.avalon.service.HeroService;
import com.avalon.service.UserService;

@Controller
@RequestMapping("collection")
public class CollectionController {

    @Autowired
    private UserService userService;

    @Autowired
    private HeroService heroService;

    @RequestMapping("show")
    public String show(HttpSession session, Model model) {

        String userId = (String) session.getAttribute("id");
        User user = userService.queryById(userId);

        String userHeroCollection = user.getHeroCollection().trim();

        List<Hero> heroList = new ArrayList<Hero>();
        if (userHeroCollection != null) {
            String[] heroIdList = userHeroCollection.split(",");
            for (int i = 0; i < heroIdList.length; i++) {
                if ("".equals(heroIdList[i].trim())) {
                    continue;
                }
                int heroId = Integer.parseInt(heroIdList[i]);
                Hero hero = heroService.queryById(heroId);
                heroList.add(hero);
            }
        }
        
        model.addAttribute("heroList", heroList);
        return "/WEB-INF/views/user/myCollection.jsp";
    }
}
