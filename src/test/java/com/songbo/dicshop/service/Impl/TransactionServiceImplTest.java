package com.songbo.dicshop.service.Impl;

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

        try {
            transactionService.createNewAddr(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}