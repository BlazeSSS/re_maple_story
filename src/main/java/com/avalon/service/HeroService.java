package com.avalon.service;

import java.util.List;

import com.avalon.domain.Hero;

public interface HeroService {

    // 查询
    public Hero queryById(int id);

    // 查询列表（查询所有）
    public List<Hero> queryAll(int currentPage, int pageSize);

    // 模糊查询
    public List<Hero> queryLike(String name, int currentPage, int pageSize);
}
