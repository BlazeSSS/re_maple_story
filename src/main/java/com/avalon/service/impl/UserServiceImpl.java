package com.avalon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avalon.domain.User;
import com.avalon.mapper.UserMapper;
import com.avalon.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public int register(User user) {
        return userMapper.register(user);
    }

    @Override
    public int deleteUser(String id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public int updateFreezeStatus(User user) {
        return userMapper.updateFreezeStatus(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int updateCollection(User user) {
        return userMapper.updateCollection(user);
    }

    @Override
    public User queryById(String id) {
        return userMapper.queryById(id);
    }

    @Override
    public List<User> queryAll(int currentPage, int pageSize) {
        Page<User> page = PageHelper.startPage(currentPage, pageSize);
        List<User> list = userMapper.queryAll();
        return list;
    }

    @Override
    public List<User> queryLike(String name, int currentPage, int pageSize) {
        Page<User> page = PageHelper.startPage(currentPage, pageSize);
        return userMapper.queryLike(name);
    }
}
