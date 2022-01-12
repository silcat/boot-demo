package com.boot.demo;

import com.boot.demo.bean.User;
import com.boot.demo.service.UserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class XaTest extends BaseServiceTest {
    @Autowired
    public UserService userService;

    @Test
    public void insert()  {
        userService.test();
    }

}
