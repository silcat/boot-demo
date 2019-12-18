package com.boot.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.boot.demo.common.ResultStatus;


/**
 * Json工具类
 */
public class JsonUtils {

    /**
     * 对象转成Json
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 对象转成Json字符串
     */
    public static String toJsonString(Object obj) {
        return JSON.toJSONString(obj);
    }
    /**
     * Json字符串转成json对象
     */
    public static JSONObject toJsonObject(String json) {
        return JSON.parseObject(json);
    }
    /**
     * Json字符串转成对象
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }


    public static <T> T toObject(String json, TypeReference<T> trf) {
        return JSON.parseObject(json, trf);
    }


    public static JSONObject initJSONObjectSuccess(ResultStatus status, boolean success) {
        JSONObject data = initJSONObject(status.getCode(), status.getMsg());
        data.put("success", success);
        return data;
    }

    public static JSONObject initJSONObjectUidOrderId(ResultStatus status, boolean success, Long uid, String orderId) {
        JSONObject data = initJSONObjectSuccess(status, success);
        data.put("uid", uid);
        data.put("order_id", orderId);
        return data;
    }

    public static JSONObject initJSONObjectEncryptionOrderId(ResultStatus status, boolean success, String encryption, String orderId) {
        JSONObject data = initJSONObjectSuccess(status, success);
        data.put("encryption", encryption);
        data.put("order_id", orderId);
        return data;
    }

    public static JSONObject initJSONObject(int code, String msg) {
        JSONObject data = new JSONObject();
        data.put("code", code);
        data.put("msg", msg);
        return data;
    }

    public static JSONObject initJSONObject(ResultStatus status) {
        return initJSONObject(status.getCode(), status.getMsg());
    }

    public static JSONObject initJSONObjectNodeKey(ResultStatus status, String nodeKey) {
        JSONObject data = initJSONObject(status);
        data.put("node_key", nodeKey);
        return data;
    }

    public static JSONObject initJSONObjectUid(Long uid) {
        JSONObject data = new JSONObject();
        data.put("uid", uid);
        return data;
    }

    public static JSONObject initJSONObjectCreditRequestId(String creditRequestId) {
        JSONObject data = new JSONObject();
        data.put("credit_request_id", creditRequestId);
        return data;
    }

    public static JSONObject initJSONObjectAlarm(String content, String partyId) {
        JSONObject data = new JSONObject();
        data.put("content", content);
        data.put("partyId", partyId);
        return data;
    }

    public static JSONObject initJSONObjectReportAlarm(String content, String partyId, String title, String type) {
        JSONObject data = new JSONObject();
        data.put("content", content);
        data.put("partyId", partyId);
        data.put("title", title);
        data.put("type", type);
        return data;
    }

}