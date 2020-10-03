package com.avalon.mapper;

import java.util.List;

import com.avalon.domain.User;

public interface UserMapper {
    // 登录、查询
    public User login(User user);

    // 注册
    public int register(User user);

    // 删除
    public int deleteUser(String id);

    // 解冻/冻结
    public int updateFreezeStatus(User user);

    // 更新
    public int updateUser(User user);

    // 更新收藏
    public int updateCollection(User user);

    // 单个查询
    public User queryById(String id);

    // 查询列表（查询所有）
    public List<User> queryAll();

    // 模糊查询
    public List<User> queryLike(String name);
}
