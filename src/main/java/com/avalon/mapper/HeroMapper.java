package com.avalon.mapper;

import java.util.List;

import com.avalon.domain.Hero;

public interface HeroMapper {

    // 查询
    public Hero queryById(int id);

    // 查询列表（查询所有）
    public List<Hero> queryAll();

    // 模糊查询
    public List<Hero> queryLike(String name);
}
