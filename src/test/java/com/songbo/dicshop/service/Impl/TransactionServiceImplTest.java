package com.songbo.dicshop.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.exception.AddrException;
import com.songbo.dicshop.service.TransactionService;
import com.songbo.dicshop.utils.NumberUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionServiceImplTest {

    @Autowired
    private TransactionService transactionService;
    @Test
    void createNewAddr() {

        /*try {
            transactionService.createNewAddr(1);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Test
    void getAddr() {
    }

    @Test
    void getAmount() {
        /*Long i = 9000000000000000l;
        Double o = Double.valueOf(i);
        System.out.println(o);*/
        try {
            Double amount = transactionService.getAmount(1);
            String s = NumberUtil.formatDouble(amount);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void rechaege() {
        try {
            JSONObject rechaege = transactionService.rechaege(1, 100.0, 1);
            System.out.println(rechaege);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getNowHash() {
        try {
            System.out.println(transactionService.getNowHash());
            String nowHash = transactionService.getNowHash();
            char[] chars = nowHash.toCharArray();

            int seeds = chars[nowHash.length()-1];
            if (Character.isDigit(chars[nowHash.length()-1])){
                seeds -= '0';
            }
            System.out.println(seeds);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}