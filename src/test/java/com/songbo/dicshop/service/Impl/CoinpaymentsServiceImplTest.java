package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.bean.coinpayments.GetTxInfoRequest;
import com.songbo.dicshop.service.CoinpaymentsService;
import com.songbo.dicshop.utils.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoinpaymentsServiceImplTest {

    @Autowired
    private CoinpaymentsService coinpaymentsService;
    @Test
    void createTransaction() {
    }

    @Test
    void getTxInfo() {
        try {
            System.out.println(coinpaymentsService.getTxInfo(new GetTxInfoRequest(CommonUtil.COINPAYMENTS_GET_TX_INFO, "CPED6WSJ30ZWADVRAKIYFP1PRB")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}