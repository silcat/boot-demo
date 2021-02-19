package com.boot.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.demo.bean.Test;
import com.boot.demo.bean.User;
import com.boot.demo.mapper.UserMapper;
import com.boot.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User list(int age) {
        Wrapper<User> queryWrapper = Wrappers.<User>query()
                .lambda()
                .eq(User::getAge, age)
                .last("limit 1");
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public String insertForeach(User userVOList) {
        if (true){
            throw new RuntimeException();
        }

        userMapper.insert(userVOList);

        return "";
    }
}
