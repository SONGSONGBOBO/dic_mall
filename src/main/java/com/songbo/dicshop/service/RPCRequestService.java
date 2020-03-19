package com.songbo.dicshop.service;

import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.bean.chainResult.CurrentResult;


/**
 * @ClassName RPCRequestService
 * @Description TODO
 * @Author songbo
 * @Date 19-9-21 下午1:58
 **/

public interface RPCRequestService {

    <T,V> CurrentResult<T, V> CurrentRPCRequest(
            JSONObject params, Class<T> tClass, Class<V> vClass);
}
