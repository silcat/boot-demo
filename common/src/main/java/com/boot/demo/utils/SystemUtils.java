package com.boot.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * Created by sunlichuan on 18-9-10
 */
@Slf4j
public class SystemUtils {

    private static final Properties p = System.getProperties();

    // 获取系统参数
    public static String getSystemProperty(String property) {
        return p.getProperty(property);
    }

    // 获取系统参数
    public static String getSystemProperty(String property, String defaultValue) {
        return p.getProperty(property, defaultValue);
    }

    public static String getEnv(String envKey) {
        return System.getenv(envKey);
    }

    public static void main(String[] args) {
        p.list(System.out);
        System.getenv().forEach((key, value) -> System.out.println(key + "--" + value));
    }
}
