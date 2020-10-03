package com.avalon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avalon.domain.Hero;
import com.avalon.mapper.HeroMapper;
import com.avalon.service.HeroService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class HeroServiceImpl implements HeroService {

    @Autowired
    private HeroMapper heroMapper;
    
    @Override
    public Hero queryById(int id) {
        return heroMapper.queryById(id);
    }

    @Override
    public List<Hero> queryAll(int currentPage, int pageSize) {
        Page<Hero> page = PageHelper.startPage(currentPage, pageSize);
        return heroMapper.queryAll();
    }

    @Override
    public List<Hero> queryLike(String name, int currentPage, int pageSize) {
        Page<Hero> page = PageHelper.startPage(currentPage, pageSize);
        return heroMapper.queryLike(name);
    }
}
