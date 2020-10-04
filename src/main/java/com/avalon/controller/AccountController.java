package com.avalon.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avalon.domain.User;
import com.avalon.service.UserService;
import com.avalon.vo.UserVo;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    private UserService userService;
    
    @RequestMapping("login")
    public String login(UserVo userVo, Model model, HttpSession session) {
        
        if (!"login".equals(userVo.getAction())) {
            return "../login.jsp";
        }
        
        User loginUser = userService.login(userVo);
        
        if (loginUser != null) {
            if (loginUser.getIsFreeze() == 1) {
                model.addAttribute("message", "账号被冻结 ");
                return "../login.jsp";
            } else {
                session.setAttribute("id", loginUser.getId());
                session.setAttribute("name", loginUser.getName());
                return "../welcome.jsp";
            }
        } else {
            model.addAttribute("message", "登录失败");
            return "../login.jsp";
        }
    }
    
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        
        session.removeAttribute("id");
        return "../login.jsp";
    }
    
    @RequestMapping("register")
    public String register(UserVo userVo, Model model) {
        
        if (!"register".equals(userVo.getAction())) {
            return "../register.jsp";
        }
        
        int count = userService.register(userVo);
        if (count != 0) {
            model.addAttribute("message", "注册成功");
            return "../login.jsp";
        } else {
            model.addAttribute("message", "注册失败，账号已存在");
            return "../register.jsp";
        }
    }
}
