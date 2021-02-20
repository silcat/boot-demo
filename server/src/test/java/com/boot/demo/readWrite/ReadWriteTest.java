package com.boot.demo.readWrite;


import com.boot.demo.bean.User;
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
        for (int i=2;i<10;i++){
            User user1 = userService.list(i);
            System.out.println(user1.getDb());
        }
    }
    @Test
    public void insert()  {
        com.boot.demo.bean.Test test = new com.boot.demo.bean.Test();
        test.setTestMessage("test");
        test.setId(12);
        testMapper.insert(test);

    }
}
