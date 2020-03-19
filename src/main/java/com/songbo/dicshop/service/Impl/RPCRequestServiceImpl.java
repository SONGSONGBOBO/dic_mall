package com.songbo.dicshop.service.Impl;

import com.alibaba.fastjson.JSONObject;

import com.songbo.dicshop.bean.chainResult.CurrentResult;
import com.songbo.dicshop.service.RPCRequestService;
import com.songbo.dicshop.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
}
