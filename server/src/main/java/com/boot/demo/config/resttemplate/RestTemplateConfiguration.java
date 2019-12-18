package com.boot.demo.config.resttemplate;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.boot.demo.config.common.ServerProperties;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Configuration
@ConditionalOnClass(value = {RestTemplate.class, HttpClient.class})
@EnableConfigurationProperties(ServerProperties.class)
public class RestTemplateConfiguration {

    @Resource
    private ServerProperties serverProperties;

    /**
     * 通用的restTemple
     *
     * @return
     */
    @Primary
    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        return baseRestTemplate(commonHttpRequestFactory());
    }

    @Primary
    @Bean
    public ClientHttpRequestFactory commonHttpRequestFactory() {
        return baseHttpRequestFactory(commonHttpClient());
    }

    @Primary
    @Bean
    public HttpClient commonHttpClient() {
        return baseHttpClient(null);
    }


    /**
     * 设置请求模板：序列化设置
     *
     * @return
     */
    private RestTemplate baseRestTemplate(ClientHttpRequestFactory requestFactory) {

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();

        //重新设置StringHttpMessageConverter字符集为UTF-8，解决中文乱码问题
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        converterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect);

        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        //TODO @yangtianteng 是否对其他converter造成影响
        converterList.add(1, fastJsonHttpMessageConverter);

        return restTemplate;
    }

    /**
     * 设置连接工厂：设置超时时间
     *
     * @param httpClient
     * @return
     */
    private HttpComponentsClientHttpRequestFactory baseHttpRequestFactory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(serverProperties.getConnectTimeout()*1000);
        factory.setReadTimeout(serverProperties.getReadTimeout()*1000);
        factory.setConnectionRequestTimeout(serverProperties.getConnectionRequestTimeout()*1000);
        return factory;
    }


    /**
     * 设置httpClient：设置连接池及身份认证
     *
     * @return
     */
    private HttpClient baseHttpClient(CredentialsProvider provider) {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        //设置连接池
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(serverProperties.getMaxTotal());
        connectionManager.setDefaultMaxPerRoute(serverProperties.getDefaultMaxPerRoute());
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setConnectionManager(connectionManager);
        if (Optional.ofNullable(provider).isPresent()) {
            httpClientBuilder.setDefaultCredentialsProvider(provider);
        }
        return httpClientBuilder.build();

    }


}
