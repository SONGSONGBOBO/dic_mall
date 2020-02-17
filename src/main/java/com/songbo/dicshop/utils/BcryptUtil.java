package com.songbo.dicshop.utils;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @ClassName BcryptUtil
 * @Description TODO
 * @Author songbo
 * @Date 19-8-2 下午1:37
 **/
public class BcryptUtil {
    /*加密*/
    public static String encryption(String word){
        String hashed = BCrypt.hashpw(word,BCrypt.gensalt());
        return hashed;
    }
    /*解密*/
    public static boolean decryption(String word,String hashed){
        return BCrypt.checkpw(word,hashed);
    }

    public static void main(String[] args) {
       /* String pwt = BcryptUtil.encryption("ss6522011");
        //String pwt1 = BcryptUtil.encryption("qwdasd");
        //System.out.println(pwt+' '+pwt1);
        boolean b = BcryptUtil.decryption("ss6522011", pwt);
        System.out.println(pwt);
        System.out.println(b);*/

        String regex = "^\\s*[-||+]*[0-9]+";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher("  43 www");
        String s = "";

        int i = 0;
        while (m.find()) {
            if (m.group(i).matches(regex)) {
                s = m.group(i);
            }
            i++;
        }
        System.out.println("s: "+s);
        String[] s1 = s.split(" ");
        for (String s2 : s1) {
            if (!s2.isEmpty()) {
                System.out.println("s2: "+s2);
                System.out.println(Integer.valueOf(s2));

            }
        }

    }
}
