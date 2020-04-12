package com.songbo.dicshop.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.songbo.dicshop.bean.chainResult.CurrentResult;
import com.songbo.dicshop.service.RPCRequestService;
import com.songbo.dicshop.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.impl.client.HttpClients;
import org.brunocvcunha.coinpayments.CoinPayments;
import org.brunocvcunha.coinpayments.model.BasicInfoResponse;
import org.brunocvcunha.coinpayments.model.ResponseWrapper;
import org.brunocvcunha.coinpayments.requests.CoinPaymentsBasicAccountInfoRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RPCRequestServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2019/12/26 下午12:44
 **/
@Service
@Slf4j
public class RPCRequestServiceImpl implements RPCRequestService {


    @Override
    public <T, V> CurrentResult<T, V> CurrentRPCRequest(JSONObject params, Class<T> tClass, Class<V> vClass) {
        log.warn("rpcRequest: "+params,params);
        final String credentials = Credentials.basic(CommonUtil.NODE_LOGIN,CommonUtil.NODE_PASS);
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType json = MediaType.parse("Content-type; application/json");
        RequestBody requestBody = RequestBody.create(json,params.toJSONString());

        Request request = new Request.Builder()
                .url(CommonUtil.NODE_ADDRESS)
                .post(requestBody)
                .addHeader("Content-type", "application/json")
                .addHeader("Authorization", credentials)
                .build();
        Call call = okHttpClient.newCall(request);
        log.warn("request.body: "+request.toString(),request);
        try {
            Response response = call.execute();
            //response.body().string()只能调用一次
            String res = response.body().string();
            log.warn("response.body: "+res,response);
            /*Type type = new TypeReference<RPCResponseForT<T, V>>(){}.getType(); // 构造TypeReference的匿名内部类对象，直接获取Type对象
            RPCResponseForT<T, V> parsed = JSON.parseObject(res, type);*/
            JSONObject jsonObject = (JSONObject) JSONObject.parse(res);
            T t =jsonObject.getObject("result",tClass);
            /*if (jsonObject.getObject("result",tClass)==null){
                t = null;
            }else {
                t = jsonObject.getJSONObject("result").toJavaObject(tClass);
            }*/

            V v = jsonObject.getObject("error",vClass);;
            /*if (jsonObject.getJSONObject("error")==null){
                v = null;
            }else {
                v = jsonObject.getJSONObject("error").toJavaObject(vClass);
            }*/
            String id = jsonObject.getString("id");
            CurrentResult<T, V> responseForT = new CurrentResult<>(t, v, id);
            return responseForT;

        } catch (IOException e) {
            e.printStackTrace();
            log.warn("GET_fail "+e);
            return null;
        }
    }

    @Override
    public <T> T rpcRequest(String url, String method,Map<String, String> header, Object data, Class<T> tClass) {
        return null;
    }

    @Override
    public <T> T currentRequest(String url, String method, Headers headers, Object data, Class<T> tClass) throws Exception {


        OkHttpClient okHttpClient = new OkHttpClient();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(1000, TimeUnit.SECONDS);
        builder.readTimeout(1000, TimeUnit.SECONDS);
        MediaType type = MediaType.parse("Content-type; "+headers.get("Content-Type"));

        Request request;
        if ("get".equals(method)) {
            request = new Request.Builder()
                    .url(url)
                    .headers(headers)
                    .build();
        } else {
            RequestBody requestBody = RequestBody.create(type, JSONObject.toJSONString(data));
            log.warn("requestBody: " + JSONObject.toJSONString(data) + requestBody.toString(), requestBody);
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader(headers.name(0),headers.value(0))
                    .addHeader(headers.name(1), headers.value(1))
                    .build();

        }
        System.out.println(request.headers());
        Call call = okHttpClient.newCall(request);
        log.warn("request.body: " + request.toString(), request);
        try {
            Response response = call.execute();
            if (response.code() != 200) {
                log.error(response.message());
                throw new Exception(response.message());
            }
            String res = response.body().string();
            log.warn("response.body: " + res, response);

            JSONObject jsonObject = (JSONObject) JSONObject.parse(res);
            return jsonObject.toJavaObject(tClass);
        } catch (Exception e) {
            log.warn("GET_fail " + e);
            throw new Exception(e.getMessage());
        }
    }
}
