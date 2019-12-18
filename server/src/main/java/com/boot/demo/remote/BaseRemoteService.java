package com.boot.demo.remote;

import com.boot.demo.exception.ThirdPartyRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
public abstract class BaseRemoteService {

    public RestTemplate restTemplate;

    /**
     * 请求通用方法
     *
     * @param url
     * @param uriVars 请求参数
     * @return
     */
    public String get(String url, Map<String, String> uriVars) {
        setRestTemplate();
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, uriVars);
            log.info("URL:{} uriVars:{} response:{}", url, uriVars, responseEntity.toString());
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                throw new ThirdPartyRequestException();
            }
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error("URL:{} uriVars:{} response:{}", url, uriVars, e.getCause());
            if (e instanceof HttpServerErrorException) {
                throw new ThirdPartyRequestException(((HttpServerErrorException) e).getRawStatusCode() + "");
            } else {
                throw e;
            }
        }
    }

    /**
     * post请求通用方法
     *
     * @param url
     * @param uriVars 请求参数
     * @return
     */
    public String post(String url, Object uriVars) {
        setRestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> formEntity = new HttpEntity<>(uriVars, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, formEntity, String.class);
            if (responseEntity.getStatusCode().value() != HttpStatus.OK.value()) {
                throw new ThirdPartyRequestException(responseEntity.toString());
            }
            log.info("URL:{} uriVars:{} respond:{}", url, uriVars.toString(),responseEntity.getBody());
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error("URL:{} uriVars:{} ", url, uriVars.toString(), e);
            if (e instanceof HttpServerErrorException) {
                throw new ThirdPartyRequestException(((HttpServerErrorException) e).getRawStatusCode() + "");
            } else {
                throw e;
            }
        }
    }

    /**
     * post请求通用方法
     *
     * @param url     不包含host
     * @param uriVars 请求参数
     * @return
     */
    public <T> String post(String url, T uriVars, HttpHeaders headers) {
        setRestTemplate();
        try {
            HttpEntity<T> formEntity = new HttpEntity(uriVars, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, formEntity, String.class);
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                throw new ThirdPartyRequestException(responseEntity.getStatusCodeValue() + "");
            }
            log.info("URL:{} uriVars:{} response:{}", url, uriVars.toString(), responseEntity.toString());
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error("URL:{} uriVars:{} response:{}", url, uriVars.toString(), e.getCause());
            if (e instanceof HttpServerErrorException) {
                throw new ThirdPartyRequestException(((HttpServerErrorException) e).getRawStatusCode() + "");
            } else {
                throw e;
            }
        }
    }

    protected abstract void setRestTemplate();
}
