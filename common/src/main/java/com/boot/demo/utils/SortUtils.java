package com.boot.demo.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by sunlichuan on 18-11-8
 */
public class SortUtils {


    public static int getByOverdueDays(JSONObject data) {
        return data.getIntValue("overdueDays");
    }

    public static long getByBillPlanDate(JSONObject data) {
        return data.getLongValue("billPlanDate");
    }
}
