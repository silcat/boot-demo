package com.boot.demo.utils;

import java.util.zip.CRC32;

/**
 * @author sunlichuan
 * Created by sunlichuan on 19-2-18
 */
public class Crc32Utils {

    public static long getValueFromString(String creditRequestId) {
        // 根据规则进行过滤
        CRC32 crc32 = new CRC32();
        crc32.update(creditRequestId.getBytes());
        return crc32.getValue();
    }
}
