package com.songbo.dicshop.bean.payments;

import lombok.Data;

/**
 * @ClassName CreatePayment
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/23 下午3:15
 **/
@Data
public class CreatePaymentResult {

    private String payment_id;
    private String payment_status;
    private String pay_address;
    private Double price_amount;
    private String price_currency;
    private Double pay_amount;
    private String pay_currency;
    private String order_id;
    private String order_description;
    private String ipn_callback_url;
    private String created_at;
    private String updated_at;
    private String purchase_id;

}
