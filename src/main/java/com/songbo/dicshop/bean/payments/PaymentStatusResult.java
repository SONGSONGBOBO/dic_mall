package com.songbo.dicshop.bean.payments;

import lombok.Data;

/**
 * @ClassName PaymentStatusResult
 * @Description TODO
 * {
 *   "payment_id": "<your_payment_id>",
 *   "payment_status": "waiting",
 *   "pay_address": "<your_payment_address>",
 *   "price_amount": 3999.5,
 *   "price_currency": "usd",
 *   "pay_amount": 0.8102725,
 *   "actually_paid": 0,
 *   "pay_currency": "btc",
 *   "created_at": "2019-04-18T13:39:27.982Z",
 *   "updated_at": "2019-04-18T13:40:16.512Z",
 *   "purchase_id": "<your_purchase_id",
 *   "outcome_currency": "eth",
 *   "outcome_amount": 31.28
 * }
 * @Author songbo
 * @Date 2020/3/23 下午8:50
 **/
@Data
public class PaymentStatusResult {
    private String payment_id;
    private String payment_status;
    private String pay_address;
    private Double price_amount;
    private String price_currency;
    private Double pay_amount;
    private Double actually_paid;
    private String pay_currency;
    private String created_at;
    private String updated_at;
    private String purchase_id;
    private String outcome_currency;
    private Double outcome_amount;


}
