package com.songbo.dicshop.utils;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName TimeUtil
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/20 上午12:04
 **/
public class TimeUtil {

    public static String getNow() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        String b = "asdadada1lla";
        char[] s = b.toCharArray();
        int a = s[s.length-1];
        System.out.println(a);
    }
}
