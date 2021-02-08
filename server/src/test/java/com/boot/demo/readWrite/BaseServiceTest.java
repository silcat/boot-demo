package com.boot.demo.readWrite;


import com.boot.demo.bean.User;
import com.boot.demo.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.soap.SOAPBinding;

public class BaseServiceTest extends com.boot.demo.BaseServiceTest {
    @Autowired
    public UserService userService;
    @Test
    public void readWrite()  {
        for (int i=2;i<10;i++){
            User user1 = userService.list(i);
            System.out.println(user1.getDb());
        }
    }
    @Test
    public void insert()  {
        User user = new User();
        for (int i=5;i<10;i++){
            user.setAge(i);
            userService.insertForeach(user);
        }

    }
}
