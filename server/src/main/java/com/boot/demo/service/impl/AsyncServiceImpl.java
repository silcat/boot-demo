package com.boot.demo.service.impl;


import com.boot.demo.service.IAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncServiceImpl implements IAsyncService {

    @Override
    @Async("asyncExecutor")
    public void asynMq() {

    }

}
