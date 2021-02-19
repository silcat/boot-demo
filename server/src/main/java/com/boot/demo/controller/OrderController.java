package com.boot.demo.controller;

import com.boot.demo.bean.User;
import com.boot.demo.service.UserService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    private UserService userService;

    @RequestMapping("update")
    String update(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money, @RequestParam("status") Integer status){
        User user = new User();
        user.setAge(10);
        if (userId == 10){
            throw new RuntimeException();
        }
        userService.insertForeach(user);
        return "通知成功";
    }
}
