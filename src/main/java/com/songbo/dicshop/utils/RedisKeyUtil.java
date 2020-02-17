package com.songbo.dicshop.utils;

/**
 * @ClassName RedisKeyUtil
 * @Description TODO
 * @Author songbo
 * @Date 19-10-10 下午3:55
 **/
public class RedisKeyUtil {

    public final static String redis_pre_key = "dic::";

    public final static String account_token_key = "account";

    /**
      *@Description TODO 根据::分割redis字符串
      *@param
      *@return
    **/
    public static String[] keySplitEmp(String s){

        String delimeter = "::";
        String[] temp;
        temp = s.split(delimeter);
        return temp;
    }
}
