package com.boot.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by baomingli on 2018/10/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UCreditApplication.class,webEnvironment= SpringBootTest.WebEnvironment.MOCK)
public class BaseServiceTest {
}
