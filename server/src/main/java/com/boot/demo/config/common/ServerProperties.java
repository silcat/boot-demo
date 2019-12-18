package com.boot.demo.config.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties
public class ServerProperties {

    /**
     * ======================== restTemplate配置 ================================
     */
    @Value("${thirdparty.rest-template.readTimeout}")
    private int readTimeout;
    @Value("${thirdparty.rest-template.connectTimeout}")
    private int connectTimeout;
    @Value("${thirdparty.rest-template.connectionRequestTimeout}")
    private int connectionRequestTimeout ;
    @Value("${thirdparty.rest-template.maxTotal}")
    private int maxTotal ;
    @Value("${thirdparty.rest-template.defaultMaxPerRoute}")
    private int defaultMaxPerRoute ;
    /**
     * ======================== remote配置 ================================
     */
    @Value("${thirdparty.remote.host}")
    private String remoteHost;
    @Value("${thirdparty.remote.test}")
    private String piccUrlRemoteTest;

}
