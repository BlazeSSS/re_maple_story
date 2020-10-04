package com.avalon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avalon.domain.Monster;
import com.avalon.service.MonsterService;

@Controller
@RequestMapping("monster")
public class MonsterController {

    @Autowired
    private MonsterService monsterService;
    
    @RequestMapping("show")
    public String show() {
        
        return "/WEB-INF/views/detail/detailMonster.jsp";
    }
    
    @PostMapping("switch/{id}")
    @ResponseBody
    public Monster switchMonster(@PathVariable("id") Integer id) {
        
        Monster monster = monsterService.queryById(id);
        return monster;
    }
}
