package com.avalon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avalon.domain.Monster;
import com.avalon.mapper.MonsterMapper;
import com.avalon.service.MonsterService;

@Service
public class MonsterServiceImpl implements MonsterService{

    @Autowired
    private MonsterMapper monsterMapper;
    
    @Override
    public Monster queryById(int id) {
        return monsterMapper.queryById(id);
    }
}
