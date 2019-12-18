package com.boot.demo.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author baomingli
 * Created by bml on 2018-4-4.
 */
@Slf4j
public class VHttpUtil {

    public static final int STATUS_OK = 200;
    private static PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();

    static {
        clientConnectionManager.setMaxTotal(200);
        clientConnectionManager.setDefaultMaxPerRoute(100);
    }

    public static CloseableHttpClient getHttpClient() {
        return getHttpClient(null);
    }

    public static CloseableHttpClient getHttpClient(RequestConfig config) {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.custom()
                    .setConnectionManager(clientConnectionManager)
                    .setConnectionManagerShared(true)
                    .setDefaultRequestConfig(config == null ? requestConfig() : config)
                    .setRetryHandler(retryHandler())
                    .setKeepAliveStrategy((response, context) -> 30 * 1000)
                    .build();
        } catch (Exception e) {
            log.error("custom http client failed!", e);
        }
        return httpClient;
    }

    public static RequestConfig requestConfig() {
        RequestConfig requestConfig = RequestConfig
                .custom()
                .setCookieSpec("standard-strict")
                .setConnectionRequestTimeout('\uea60')
                .setConnectTimeout('\uea60')
                .setSocketTimeout('\uea60')
                .build();
        return requestConfig;
    }

    public static HttpRequestRetryHandler retryHandler() {
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 5) {
                    return false;
                } else if (exception instanceof NoHttpResponseException) {
                    return true;
                } else if (exception instanceof SSLHandshakeException) {
                    return false;
                } else if (exception instanceof InterruptedIOException) {
                    return false;
                } else if (exception instanceof UnknownHostException) {
                    return false;
                } else if (exception instanceof ConnectTimeoutException) {
                    return false;
                } else if (exception instanceof SSLException) {
                    return false;
                } else {
                    HttpClientContext clientContext = HttpClientContext.adapt(context);
                    HttpRequest request = clientContext.getRequest();
                    return !(request instanceof HttpEntityEnclosingRequest);
                }
            }
        };
        return httpRequestRetryHandler;
    }

    public static HttpResultVo httpPost(Map<String, String> paramMap, String httpUrl, Map<String, String> headers) throws IOException {
        if (paramMap == null || paramMap.isEmpty()) {
            log.error("[httpPost]map请求参数不能为空!");
            return null;
        }
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(httpUrl);
        if (headers != null && headers.size() > 0) {
            Header[] headerArray = headers.entrySet().stream()
                    .map(x -> new BasicHeader(x.getKey(), x.getValue()))
                    .toArray(BasicHeader[]::new);
            httpPost.setHeaders(headerArray);
        }
        List<NameValuePair> nvpList = paramMap.entrySet().stream()
                .map(x -> new BasicNameValuePair(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
        httpPost.setEntity(new UrlEncodedFormEntity(nvpList, "UTF-8"));

        log.info("Executing request: {}", httpPost.getRequestLine());
        CloseableHttpResponse response1 = httpClient.execute(httpPost);
        String result = EntityUtils.toString(response1.getEntity());
        log.info("Executing response: {}", result);
        return new HttpResultVo(response1.getStatusLine().getStatusCode(), result);
    }

    public static HttpResultVo httpPost(String json, String httpUrl, Map<String, String> headers, Integer timeout) throws IOException {
        return httpPost(json, httpUrl, headers, timeout, null, null);
    }


    public static HttpResultVo httpPost(String json, String httpUrl, Map<String, String> headers, Integer timeout, String userName, String password) throws IOException {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            if (null != timeout) {
                RequestConfig requestConfig = RequestConfig
                        .custom()
                        .setCookieSpec("standard-strict")
                        .setConnectTimeout(timeout * 1000)
                        .setSocketTimeout(timeout * 1000)
                        .setConnectionRequestTimeout(timeout * 1000)
                        .build();
                httpClient = getHttpClient(requestConfig);
            } else {
                httpClient = getHttpClient();
            }
            HttpClientContext context = null;

            if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password)) {
                CredentialsProvider provider = new BasicCredentialsProvider();
                UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(userName, password);
                provider.setCredentials(AuthScope.ANY, credentials);
                // Add AuthCache to the execution context
                context = HttpClientContext.create();
                context.setCredentialsProvider(provider);
            }

            if (headers != null) {
                int i = 0;
                Header[] headerArray = new Header[headers.size()];
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    headerArray[i] = new BasicHeader(entry.getKey(), entry.getValue());
                    i++;
                }
                httpPost.setHeaders(headerArray);
            }
            if (!StringUtils.isBlank(json)) {
                httpPost.setEntity(new StringEntity(json, Charset.forName("UTF-8")));
            }
            log.info("Executing request: {},parameter: {}", httpPost.getRequestLine(), json);
            CloseableHttpResponse response1 = httpClient.execute(httpPost, context);
            String result = EntityUtils.toString(response1.getEntity());
            log.info("Executing response: {},request: {},parameter: {}", result, httpPost.getRequestLine(), json);
            return new HttpResultVo(response1.getStatusLine().getStatusCode(), result);
        }catch (Exception e){
            log.error("", e);
            throw e;
        } finally {
            try {
                httpPost.releaseConnection();
                httpClient.close();
            } catch (IOException e) {
                log.error("close http client failed!", e);
            }
        }

    }

    public static HttpResultVo httpPost(String json, String httpUrl, Map<String, String> headers) throws IOException {
        return httpPost(json, httpUrl, headers, null);
    }


    public static HttpResultVo httpGet(String httpUrl, Map<String, Object> paramMap, Map<String, String> headers) throws IOException {
        String result;
        CloseableHttpClient httpClient = getHttpClient();
        if (paramMap != null) {
            Iterator request = paramMap.entrySet().iterator();
            List<NameValuePair> params = new ArrayList<>();
            while (request.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry) request.next();
                Object value = entry.getValue();
                if (value instanceof List) {
                    for (Object obj : (List) value) {
                        params.add(new BasicNameValuePair(entry.getKey(), (String) obj));
                    }
                } else {
                    params.add(new BasicNameValuePair(entry.getKey(), value.toString()));
                }
            }
            httpUrl = httpUrl + "?" + EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
        }
        HttpGet httpGet = new HttpGet(httpUrl);
        try {
            if (headers != null) {
                int i = 0;
                Header[] headerArray = new Header[headers.size()];
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    headerArray[i] = new BasicHeader(entry.getKey(), entry.getValue());
                    i++;
                }
                httpGet.setHeaders(headerArray);
            }
            log.info("Executing request: {}", httpGet.getRequestLine());
            CloseableHttpResponse response = httpClient.execute(httpGet);
            result = EntityUtils.toString(response.getEntity());
            log.info("Executing response: {}", result);
            return new HttpResultVo(response.getStatusLine().getStatusCode(), result);

        } catch (Exception e) {
            log.error("[httpGet] get failed!", e);
            throw e;
        } finally {
            try {
                httpGet.releaseConnection();
                httpClient.close();
            } catch (IOException e) {
                log.error("close http client failed!", e);
            }
        }
    }


    public static HttpResultVo httpPut(String httpUrl, String json, Map<String, String> headers) {
        String result;
        CloseableHttpClient httpClient = getHttpClient();

        HttpPut httpPut = new HttpPut(httpUrl);
        try {
            log.info("Executing request: {}", httpPut.getRequestLine());
            int i = 0;
            Header[] headerArray = new Header[headers.size()];
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                headerArray[i] = new BasicHeader(entry.getKey(), entry.getValue());
                i++;
            }
            httpPut.setHeaders(headerArray);
            if (!StringUtils.isBlank(json)) {
                StringEntity stringEntity = new StringEntity(json, "UTF-8");
                httpPut.setEntity(stringEntity);
            }
            CloseableHttpResponse response1 = httpClient.execute(httpPut);
            result = EntityUtils.toString(response1.getEntity());
            log.info("Executing response: {}", result);
            return new HttpResultVo(response1.getStatusLine().getStatusCode(), result);
        } catch (Exception var15) {
            throw new RuntimeException(var15);
        } finally {
            try {
                httpPut.releaseConnection();
                httpClient.close();
            } catch (IOException var14) {
                log.error("", var14);
            }
        }
    }

    public static HttpResultByteVo httpPostByte(byte[] payload,
                                                String httpUrl,
                                                Map<String, String> headers) throws IOException {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            httpClient = getHttpClient();
            HttpClientContext context = null;
            if (headers != null) {
                int i = 0;
                Header[] headerArray = new Header[headers.size()];
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    headerArray[i] = new BasicHeader(entry.getKey(), entry.getValue());
                    i++;
                }
                httpPost.setHeaders(headerArray);
            }
            httpPost.setEntity(new ByteArrayEntity(payload));
            CloseableHttpResponse response1 = httpClient.execute(httpPost, context);
            byte[] resultLoad = EntityUtils.toByteArray(response1.getEntity());
            return new HttpResultByteVo(response1.getStatusLine().getStatusCode(), resultLoad);
        } catch (Exception e) {
            log.error("", e);
            throw e;
        } finally {
            try {
                httpPost.releaseConnection();
                httpClient.close();
            } catch (IOException e) {
                log.error("close http client failed!", e);
            }
        }
    }

    @Getter
    @Setter
    public static class HttpResultVo {
        private int code;
        private String message;
        public HttpResultVo(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
    @Getter
    @Setter
    public static class HttpResultByteVo {
        private int code;
        private byte[] payload;

        public HttpResultByteVo(int code, byte[] payload) {
            this.code = code;
            this.payload = payload;
        }
    }

    public static HttpResponse pushRequest(HttpPost post) {
        CloseableHttpClient httpClient = getHttpClient();
        try {
            return httpClient.execute(post);
        } catch (IOException e) {
            log.error("发送push的异常", e);
        } finally {
            try {
                post.releaseConnection();
                httpClient.close();
            } catch (IOException var14) {
                log.error("", var14);
            }
        }
        return null;
    }
}
