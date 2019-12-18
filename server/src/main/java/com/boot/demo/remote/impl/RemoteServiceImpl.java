package com.boot.demo.remote.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.boot.demo.model.server.RemoteResult;
import com.boot.demo.model.server.RemoteVo;
import com.boot.demo.remote.BaseRemoteService;
import com.boot.demo.remote.IRemoteService;
import com.boot.demo.config.common.ServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;


@Service
@Slf4j
public class RemoteServiceImpl extends BaseRemoteService implements IRemoteService {

    @Autowired
    private ServerProperties serverProperties;

    @Resource(name = "restTemplate")
    private RestTemplate restTemplate;

    @Override
    protected void setRestTemplate() {
        super.restTemplate = restTemplate;
    }
    @Override
    public RemoteResult<RemoteVo> remote() {
        String url = serverProperties.getRemoteHost() + serverProperties.getPiccUrlRemoteTest();
        String respondBody = super.post(url, new Object());
        return JSON.parseObject(respondBody, new TypeReference<RemoteResult<RemoteVo>>(){});
    }



}
