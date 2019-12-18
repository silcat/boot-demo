package com.boot.demo.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@JobHander(value = "ServerHandler")
public class ServerHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        return ReturnT.SUCCESS;
    }

}

