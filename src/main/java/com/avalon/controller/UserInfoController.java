package com.avalon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avalon.domain.User;
import com.avalon.service.UserService;
import com.avalon.util.PageDao;
import com.avalon.vo.UserVo;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("userInfo")
public class UserInfoController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("personal")
    public String Personal(Model model, HttpSession session) {
        
        String userId = (String) session.getAttribute("id");
        User user = userService.queryById(userId);
        model.addAttribute("user", user);
        return "/WEB-INF/views/user/userInfo.jsp";
    }
    
    @RequestMapping("update")
    public String Update(UserVo userVo, HttpSession session) {
        
        System.out.println("个人信息更新:" + userVo.toString());
        userService.updateUser(userVo);
        session.removeAttribute("id");
        return "../login.jsp";
    }
    
    @RequestMapping("queryLike")
    public String queryLike(String username, Model model, HttpServletRequest request) {
        
        // 分页工具类
        PageDao page = new PageDao(request);
        // 获取当前页数
        int currentPage = page.getCurrentPage();
        // 获取pageSize
        int pageSize = page.getPagesize();
        
        Page<User> list = null;
        if (username != null && !username.trim().equals("")) {
            list = (Page<User>) userService.queryLike(username, currentPage, pageSize);
        } else {
            list = (Page<User>) userService.queryAll(currentPage, pageSize);
        }
        
        int rsCount = (int) list.getTotal();
        page.setRscount(rsCount);
        String pageTools = page.pagetool(PageDao.BbsText);
        model.addAttribute("pageTools", pageTools);
        model.addAttribute("userList", list);
        return "/WEB-INF/views/user/userList.jsp";
    }
    
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") String id) {
        
        System.out.println("删除的用户id:" + id);
        int i = userService.deleteUser(id);
        System.out.println("成功删除条数:" + i);
        return "redirect:../queryLike";
    }
    
    @RequestMapping("freeze/{id}")
    public String freeze(@PathVariable("id") String id) {
        
        System.out.println("冻结的用户id:" + id);
        User user = userService.queryById(id);
        user.setIsFreeze(1);
        int i = userService.updateFreezeStatus(user);
        System.out.println("成功冻结条数:" + i);
        return "redirect:../queryLike";
    }
    
    @RequestMapping("unfreeze/{id}")
    public String unfreeze(@PathVariable("id") String id) {
        
        System.out.println("解除冻结的用户id:" + id);
        User user = userService.queryById(id);
        user.setIsFreeze(0);
        int i = userService.updateFreezeStatus(user);
        System.out.println("成功解除冻结条数:" + i);
        return "redirect:../queryLike";
    }
    
    @GetMapping("otherUser/{id}")
    public String otherUserInfo(@PathVariable("id") String id, Model model) {
        
        User user = userService.queryById(id);
        model.addAttribute("user", user);
        return "/WEB-INF/views/user/updateUser.jsp";
    }
    
    @PostMapping("otherUserUpdate")
    public String otherUserUpdate(UserVo userVo) {
        
        System.out.println("更新后的用户信息:" + userVo.toString());
        userService.updateUser(userVo);
        return "redirect:queryLike";
    }
}
