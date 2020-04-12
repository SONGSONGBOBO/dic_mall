package com.songbo.dicshop.bean.payments;

import lombok.Data;

/**
 * @ClassName CreatePaymentRequest
 * @Description TODO
 *   "price_amount": 3999.5,
 *   "price_currency": "usd",
 *   "pay_amount": 0.8102725,
 *   "pay_currency": "btc",
 *   "ipn_callback_url": "https://nowpayments.io",
 *   "order_id": "RGDBP-21314",
 *   "order_description": "Apple Macbook Pro 2019 x 1"
 * @Author songbo
 * @Date 2020/3/24 下午3:19
 **/
@Data
public class CreatePaymentRequest {

    private Double price_amount;
    private String price_currency;
    //自动生成
    private Double pay_amount;

    private String pay_currency;
    private String ipn_callback_url;
    private String order_id;
    private String order_description;

    public CreatePaymentRequest() {
    }

    public CreatePaymentRequest(Double price_amount, String price_currency, Double pay_amount, String pay_currency, String ipn_callback_url, String order_id, String order_description) {
        this.price_amount = price_amount;
        this.price_currency = price_currency;
        this.pay_amount = pay_amount;
        this.pay_currency = pay_currency;
        this.ipn_callback_url = ipn_callback_url;
        this.order_id = order_id;
        this.order_description = order_description;
    }
}
