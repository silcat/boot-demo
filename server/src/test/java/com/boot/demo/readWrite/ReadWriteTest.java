package com.boot.demo.readWrite;

import com.boot.demo.mapper.TestMapper;
import com.boot.demo.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;



public class ReadWriteTest extends com.boot.demo.BaseServiceTest {
    @Autowired
    public UserService userService;
    @Autowired
    public TestMapper testMapper;
    @Test
    public void readWrite()  {

    }
    @Test
    public void insert()  {

    }
}
