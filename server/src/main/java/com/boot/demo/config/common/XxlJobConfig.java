package com.boot.demo.config.common;

import com.boot.demo.utils.RandomPortUtils;
import com.xxl.job.core.executor.XxlJobExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;

/**
 * @ClassName XxlJobConfig
 * @Description
 * @Author zhangkuan
 * @Date 2018/7/25 12:29
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = "com.renrendai.loan.ucredit.jobhandler")
public class XxlJobConfig {

    @Value("${xxl.job.admin.addresses}")
    private String addresses;
    @Value("${xxl.job.executor.appname}")
    private String appname;
    @Value("${xxl.job.executor.ip}")
    private String ip;
    @Value("${xxl.job.executor.port}")
    private int port;
    @Value("${xxl.job.executor.logpath}")
    private String logpath;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() throws UnknownHostException {
        log.info(">>>>>>>>>>> xxl-job config init.");
        String ip = CloudConfigListener.getLocalHostLANAddress().getHostAddress();
        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
        xxlJobExecutor.setIp(ip);
        int port = RandomPortUtils.getUnUsedPort();
        log.info("xxl-job port {}", port);
        xxlJobExecutor.setPort(port);
        xxlJobExecutor.setAppName(appname);
        xxlJobExecutor.setAdminAddresses(addresses);
        xxlJobExecutor.setLogPath(logpath);
        return xxlJobExecutor;
    }
}
