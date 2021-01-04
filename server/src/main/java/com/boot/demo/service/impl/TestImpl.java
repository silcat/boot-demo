package com.boot.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.demo.bean.Test;
import com.boot.demo.mapper.TestMapper;
import com.boot.demo.service.ITestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Slf4j
@Service
public class TestImpl implements ITestService {
    @Resource
    private TestMapper testMapper;

    @Override
    public Test getByUid(Long uid) {
        Wrapper<Test> queryWrapper = Wrappers.<Test>query()
                .lambda()
                .eq(Test::getUid, uid)
                .last("limit 1");
        return testMapper.selectOne(queryWrapper);
    }

    @Override
    public void save(Long uid) {
        Test test = new Test();
        test.setUid(uid);
        test.setTestMessage("test");
        testMapper.insert(test);
    }

    @Override
    public void update(Long uid) {
        Test test = getByUid(uid);
        if (Optional.ofNullable(test).isPresent()) {
            test.setId(test.getId());
            test.setTestMessage("update");
            testMapper.updateById(test);
        }
    }
}
