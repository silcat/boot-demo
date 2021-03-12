package com.boot.demo.controller;

import com.boot.demo.bean.Order;
import com.boot.demo.bean.User;
import com.boot.demo.common.Result;
import com.boot.demo.common.ResultGenerator;
import com.boot.demo.service.IOrderService;
import com.boot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;


@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("get")
    Result<User> get(@RequestBody User user){
        return ResultGenerator.genSuccessResult(userService.list(user));
    }
    @RequestMapping("create")
    Result create(@RequestBody User user){
        userService.insert(user);
        return ResultGenerator.genSuccessResult();
    }
}
