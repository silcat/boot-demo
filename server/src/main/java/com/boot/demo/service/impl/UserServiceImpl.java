package com.boot.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.demo.bean.Test;
import com.boot.demo.bean.User;
import com.boot.demo.mapper.UserMapper;
import com.boot.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User list(User user) {
        Wrapper<User> queryWrapper = Wrappers.<User>query()
                .lambda()
                .eq(User::getUserId, user.getUserId())
                .last("limit 1");
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }
    @Override
    @Transactional
    public void testXA() {
        User user = new User();
        user.setAge(1);
        user.setUserId(4);
        this.insert(user);
        User user1 = new User();
        user1.setAge(1);
        user1.setUserId(5);
        this.insert(user1);
        int i = 1 / 0;
    }
}
