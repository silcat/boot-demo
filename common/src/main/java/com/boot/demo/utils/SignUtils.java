package com.boot.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 签名
 * @Author lijihua
 * @Date 2018/9/8 15:43
 * @Version V1.0
 */
@Slf4j
public class SignUtils {

    public static String sign(Map<String, Object> params, String signSalt) {
        String signString = buildSignSrc(params) + signSalt;
        log.error(signString);
        return MD5Util.md5(signString).toLowerCase();
    }

    /**
     * 生成待签名串
     *
     * @param obj
     *            需要签名的对象
     */
    public static String buildSignSrc(Object obj) {
        return buildSignSrc(JSON.toJSONString(obj));
    }

    /**
     * 生成待签名串
     *
     * @param jsonString
     *            需要签名的json字符串
     */
    private static String buildSignSrc(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        return buildSignSrc(jsonObject);
    }

    private static String buildSignSrc(JSONObject jsonObject) {
        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<>(jsonObject.keySet());
        keys.sort(String.CASE_INSENSITIVE_ORDER);
        StringBuilder content = new StringBuilder();
        for (String key : keys) {
            String value = jsonObject.getString(key);
            // 空串值和 sign 不参与签名
            if ("sign".equals(key) || StringUtils.isBlank(value)) {
                continue;
            }

            // 对内部对象进行排序
            try {
                JSONObject json = JSONObject.parseObject(value);
                value = JSONObject.toJSONString(json, SerializerFeature.MapSortField);
            } catch (Exception e) {}

            try {
                JSONArray jsonArray = JSONArray.parseArray(value);
                value = JSONArray.toJSONString(jsonArray, SerializerFeature.MapSortField);
            } catch (Exception e) {}

            content.append("&" + key + "=" + value);
        }

        String signSrc = content.toString();
        if (signSrc.startsWith("&")) {
            signSrc = StringUtils.removeFirst(signSrc, "&");
        }

        return signSrc;
    }
}
