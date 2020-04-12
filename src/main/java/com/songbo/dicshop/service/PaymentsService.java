package com.songbo.dicshop.service;

import com.songbo.dicshop.bean.payments.CreatePaymentRequest;
import com.songbo.dicshop.bean.payments.CreatePaymentResult;
import com.songbo.dicshop.bean.payments.EstimatedPriceResult;
import com.songbo.dicshop.bean.payments.PaymentStatusResult;

import java.util.List;

/**
  *@Description TODO now-payment
  *@param
  *@return
**/
public interface PaymentsService {
    //
    boolean getApiStatus();
    //获取api的钱包
    List<String>  getAvailableCurrencies();
    //转换，
    EstimatedPriceResult getEstimatedPrice(double amount, String from, String to);
    //
    CreatePaymentResult ceatePayment(CreatePaymentRequest createPaymentRequest);
    //curl --location --request GET 'https://api.nowpayments.io/v1/payment/<your_payment_id>' \
    //--header 'x-api-key: <your_api_key>'
    PaymentStatusResult getPaymentStatus(String paymentId);
}
