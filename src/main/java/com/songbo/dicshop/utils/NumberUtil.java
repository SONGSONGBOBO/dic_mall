package com.songbo.dicshop.utils;

import java.text.NumberFormat;

/**
 * @ClassName NumberUtil
 * @Description TODO
 * @Author songbo
 * @Date 19-9-25 下午7:43
 **/
public class NumberUtil {
    public static String formatDouble(double d) {
        NumberFormat nf = NumberFormat.getInstance();

        //nf.setMaximumIntegerDigits(20);
        //设置保留多少位小数
        nf.setMaximumFractionDigits(6);
        // 取消科学计数法
        nf.setGroupingUsed(false);
        //返回结果
        return nf.format(d);
    }
}
