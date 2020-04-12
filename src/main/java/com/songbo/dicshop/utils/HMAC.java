package com.songbo.dicshop.utils;

import okhttp3.Headers;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HMAC
 * @Description TODO
 * @Author songbo
 * @Date 2020/4/2 下午2:55
 **/
public class HMAC {


    public static String HmacSHA512( String key, String data)
    {
        return hmacSHA512(key, data,"HmacSHA512");
    }
    /**
     * 与php中的hash_hmac('sha512', $data, $key)功能相同
     * @param data
     * @param key
     * @return
     */
    private static String hmacSHA512(String key, String data,String cate) {
        String result = "";
        byte[] bytesKey = key.getBytes();
        final SecretKeySpec secretKey = new SecretKeySpec(bytesKey, cate);
        try {
            Mac mac = Mac.getInstance(cate);
            mac.init(secretKey);
            final byte[] macData = mac.doFinal(data.getBytes());
            byte[] hex = new Hex().encode(macData);

            result = new String(hex, "ISO-8859-1");
        } catch (NoSuchAlgorithmException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        //System.out.println(HMAC.HmacSHA512("6f106fbc15c9c09811c5d14d7FdAC71313a7B1B514e3428F03aF12398C3544d5",
         //       "version=1&format=json&key=bc4146d37bbeb0cd6c29833c951f11444f068bd351da02290409eb580b82e565&cmd=get_callback_address&currency=USDT.ERC20"));

       //System.out.println(HMAC.HmacSHA512("6f106fbc15c9c09811c5d14d7FdAC71313a7B1B514e3428F03aF12398C3544d5","version=1&key=bc4146d37bbeb0cd6c29833c951f11444f068bd351da02290409eb580b82e565&format=json&cmd=get_tx_info&txid=CPED4E1AXKKZNKIIG26NDWLLTE"));
        System.out.println(HMAC.HmacSHA512("b0247afe68bb7310c6d4d8a9f1177363ad3c29bf","currency=BTC"));
    }
}
