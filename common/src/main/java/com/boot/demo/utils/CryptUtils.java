package com.boot.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
public class CryptUtils {

    public static String generateMd5String(String cryptString) {
        return DigestUtils.md5Hex(cryptString);
//        String resultString = "";
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(cryptString.getBytes());
//            return new BigInteger(1, md.digest()).toString(16);
//        } catch (NoSuchAlgorithmException ex) {
//            ex.printStackTrace();
//            log.error("generate md5 failed!", ex);
//        }
//        return resultString;
    }

    public static String generateRandom(String prefix) {
        String randString = UUID.randomUUID().toString();
        String result = generateMd5String(randString);
        if (StringUtils.isNotBlank(result)) {
            return prefix + result;
        }
        return "";
    }

    public static String generateRandom() {
        String randString = UUID.randomUUID().toString();
        String result = generateMd5String(randString);
        if (StringUtils.isNotBlank(result)) {
            return result;
        }
        return "";
    }

    public static String generateRandom(int bitSize) {
        String randString = UUID.randomUUID().toString();
        String result = generateMd5String(randString);
        if (bitSize > 32) {
            return result;
        }
        return result.substring(32 - bitSize);
    }
//        return generateMd5String(strObj).toUpperCase();
//        return generateMd5String(strObj).substring(8,24);
//        return generateUniqueKeyUpper(strObj).substring(8,24);

    public static void main(String[] args) {
    System.out.println(DigestUtils.md5Hex(DateUtil.getCurrentDate() + "RRDPDF"));
    }

    public static List<?> getList(){
        List<String> list = new ArrayList<String>(2);
        list.add("a");
        list.add("b");
        return list;
    }

}
