package com.boot.demo;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by baomingli on 2018/8/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UCreditApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseControllerTest {
    @LocalServerPort
    private int port;

    private URL base;

    protected Map<String, Object> params = new HashMap<>();

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }

    protected ResponseEntity<String> post(String url) {
        return post(url, params);
    }

    protected ResponseEntity<String> post(String url, Map<String, Object> request) {
        JSONObject postData = new JSONObject();
        for (Map.Entry<String, Object> entry : request.entrySet()) {
            postData.put(entry.getKey(), entry.getValue());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(this.base + url, postData, String.class);
        System.out.println("====================================" + responseEntity.getBody());
        return responseEntity;
    }

    protected ResponseEntity<String> post(String url, Object object) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(this.base + url, object, String.class);
        System.out.println("====================================" + responseEntity.getBody());
        return responseEntity;
    }


}
