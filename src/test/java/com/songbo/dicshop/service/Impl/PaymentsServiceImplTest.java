package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.bean.payments.CreatePaymentRequest;
import com.songbo.dicshop.enums.OrderEnum;
import com.songbo.dicshop.service.PaymentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentsServiceImplTest {

    @Autowired
    private PaymentsService paymentsService;
    @Test
    void getApiStatus() {

    }

    @Test
    void getAvailableCurrencies() {
    }

    @Test
    void getEstimatedPrice() {
    }

    @Test
    void ceatePayment() {
        /*System.out.println(paymentsService.ceatePayment( new CreatePaymentRequest( 1.0,
                "usd",
                0.001,
                "zen",
                "http://47.92.86.184:8888/test/testpay",
                "test1",
                "test1-1")

        ));*/
    }

    @Test
    void getPaymentStatus() {
        //System.out.println(paymentsService.getPaymentStatus("5483226906"));
        //System.out.println(OrderEnum.);
        //System.out.println(paymentsService.getPaymentStatus("CPED6WSJ30ZWADVRAKIYFP1PRB"));
    }
}