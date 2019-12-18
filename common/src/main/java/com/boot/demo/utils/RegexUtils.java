package com.boot.demo.utils;

/**
 * Created by sunlichuan on 18-6-6
 */
public class RegexUtils {

    private static final String REGEX_TEL_NUM = "(1[3456789][0-9０-９\\-－ 　]{9,11})|([0-9]{3,4}[\\- ][0-9０-９\\-－ 　]{7,8}|\\d{7,8})";

    public static boolean isPhoneNum(String mobile) {
        return mobile.matches(REGEX_TEL_NUM);
    }
}
