package com.boot.demo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sunlichuan on 18-9-11
 */
public class MobileUtils {

    public static String trimMobile(String mobile) {
        String regex = "\\+86|\\+|-| |,|;|\\*|#|/";
        return mobile.replaceAll(regex, "");

    }


    /**
     * 移动
     * @param mobile
     * @return
     */
    public static boolean isCM(String mobile){
        String regex = "((13[4-9])|(147)|(15[0-2,7-9])|(17[8])|(18[2-4,7-8])|(198))\\d{8}|(170[5])\\d{7}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    /**
     * 联通
     * @param mobile
     * @return
     */
    public static boolean isCU(String mobile){
        String regex = "((13[0-2])|(145)|(15[5-6])|(166)|(17[156])|(18[5,6]))\\d{8}|(170[4,7-9])\\d{7}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    /**
     * 电信
     * @param mobile
     * @return
     */
    public static boolean isCT(String mobile){
        String regex = "((133)|(149)|(153)|(17[3,7])|(18[0,1,9])|(199))\\d{8}|(170[0-2])\\d{7}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String mobile = "15810452269";
        System.out.println(isCM(mobile));
        System.out.println(isCU(mobile));
        System.out.println(isCT(mobile));
        mobile = "18618145380";
        System.out.println(isCM(mobile));
        System.out.println(isCU(mobile));
        System.out.println(isCT(mobile));
    }


}
