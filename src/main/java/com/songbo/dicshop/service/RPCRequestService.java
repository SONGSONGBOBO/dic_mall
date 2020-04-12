package com.songbo.dicshop.service;

import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.bean.chainResult.CurrentResult;
import com.songbo.dicshop.bean.payments.CreatePaymentResult;
import okhttp3.Headers;

import java.util.Map;


/**
 * @ClassName RPCRequestService
 * @Description TODO
 * @Author songbo
 * @Date 19-9-21 下午1:58
 **/

public interface RPCRequestService {

    <T,V> CurrentResult<T, V> CurrentRPCRequest(
            JSONObject params, Class<T> tClass, Class<V> vClass);


    <T> T rpcRequest(String url, String method, Map<String, String > header, Object data, Class<T> tClass);

    <T> T currentRequest(String url, String method, Headers headers, Object data, Class<T> tClass) throws Exception;


}
