package com.boot.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * Created by sunlichuan on 18-9-10
 */
@Slf4j
public class RandomPortUtils {

    // 获取随机端口
    private static int randomPort() {
        // 1024~65535
        Random r = new Random();
        return r.ints(1025, 65534).findFirst().orElse(-1);
    }

    // 验证端口是否被占用
    private static boolean checkUsed(int port) {
        String command = "ss -nlp | grep " + port;
        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", command);
        try {
            Process p = pb.start();
            int exitValue = p.waitFor();
            return exitValue == 0;
        } catch (Exception e) {
            log.error("checkout port failed!", e);
        }
        return true;
//            System.out.println(exitValue);
//            if (exitValue != 0) {
//                return true;
//            }
//            // 子进程输出流信息
//            InputStream is = p.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String temp = br.readLine();
//
//            boolean result = StringUtils.isBlank(temp);
//
//            while (temp != null) {
//                System.out.println(new String(temp.getBytes("utf-8"), "utf-8"));
//                temp = br.readLine();
//            }
//            br.close();
//            is.close();
//            // 子进程错误流信息
//            is = p.getErrorStream();
//            br = new BufferedReader(new InputStreamReader(is));
//            temp = br.readLine();
//            while (temp != null) {
//                System.out.println(new String(temp.getBytes("utf-8"), "utf-8"));
//                temp = br.readLine();
//            }
//            br.close();

//            is.close();
//            return result;
    }

    public static int getUnUsedPort() {
        // 判断操作系统，如果是window指定端口
        String osName = SystemUtils.getSystemProperty("os.name");
        if (!StringUtils.equals("Linux", osName)) {
            return 18083;
        }
        int port = randomPort();
        if (!checkUsed(port)) {
            return port;
        }
        // 暂停1s
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            log.error("thread sleep failed", e);
        }
        // 递归实现
        return getUnUsedPort();
    }


    public static int getUnUsedPort(int tryTime) {
        if (tryTime <= 0) {
            return -1;
        }

        int port = randomPort();

        if (!checkUsed(port)) {
            return port;
        }
        // 暂停1s
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            log.error("thread sleep failed", e);
        }
        // 递归实现
        return getUnUsedPort(--tryTime);
    }

}
