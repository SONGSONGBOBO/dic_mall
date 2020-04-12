package com.songbo.dicshop.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.bean.payments.CreatePaymentRequest;
import com.songbo.dicshop.bean.payments.CreatePaymentResult;
import com.songbo.dicshop.bean.payments.EstimatedPriceResult;
import com.songbo.dicshop.bean.payments.PaymentStatusResult;
import com.songbo.dicshop.service.PaymentsService;
import com.songbo.dicshop.service.RPCRequestService;
import com.songbo.dicshop.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PaymentsServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/24 上午11:41
 **/
@Service
@Slf4j
public class PaymentsServiceImpl implements PaymentsService {

    @Autowired
    private RPCRequestService rpcRequestService;


    private static Map<String, String> header = new HashMap<>();

    /*public static JSONObject getHeader() {
        header.put("key", CommonUtil.NOWPAYMENTS_HEADER_KEY);
        header.put("value", CommonUtil.NOWPAYMENTS_HEADER_VALUE);
        return header;
    }*/

    static {
        //header.put("key", CommonUtil.NOWPAYMENTS_HEADER_KEY);
        //header.put("value", CommonUtil.NOWPAYMENTS_HEADER_VALUE);
        header.put(CommonUtil.NOWPAYMENTS_HEADER_KEY, CommonUtil.NOWPAYMENTS_HEADER_VALUE);
    }

    @Override
    public boolean getApiStatus() {

        JSONObject jsonObject = rpcRequestService.rpcRequest(CommonUtil.NOWPAYMENTS_URL + "status", "get", header, null, JSONObject.class);
        return jsonObject.containsKey("message") && "OK".equals(jsonObject.get("message"));
    }

    @Override
    public List<String> getAvailableCurrencies() {
        return null;
    }

    @Override
    public EstimatedPriceResult getEstimatedPrice(double amount, String from, String to) {
        return null;
    }

    @Override
    public CreatePaymentResult ceatePayment(CreatePaymentRequest createPaymentRequest) {


        return rpcRequestService.rpcRequest(
                CommonUtil.NOWPAYMENTS_URL + "payment", "post", header, createPaymentRequest, CreatePaymentResult.class);
    }

    @Override
    public PaymentStatusResult getPaymentStatus(String paymentId) {


        return rpcRequestService.rpcRequest(
                CommonUtil.NOWPAYMENTS_URL + "payment/" + paymentId, "get", header, null, PaymentStatusResult.class);

    }
}
