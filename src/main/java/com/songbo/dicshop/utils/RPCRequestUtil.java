package com.songbo.dicshop.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RPCRequestUtil
 * @Description TODO 组装rpc中请求主体，例如：
 * {"jsonrpc": "1.0", "id":"test01", "method": "getnewaddress", "params": [""] }
 * @Author songbo
 * @Date 19-9-19 下午1:58
 **/
@Slf4j
public class RPCRequestUtil {


    public static JSONObject getMainJsonParam(String method) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jsonrpc", "1.0");
        jsonObject.put("id", "dic");
        jsonObject.put("method", method);

        return jsonObject;
    }



    public static JSONObject RPCRequest0(String method){
        JSONObject jsonObject = RPCRequestUtil.getMainJsonParam(method);
        jsonObject.put("params","");
        return jsonObject;
    }

    public static JSONObject RPCRequest1(String method, Object param1){
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(param1);
        JSONObject jsonObject = RPCRequestUtil.getMainJsonParam(method);
        jsonObject.put("params",jsonArray);
        return jsonObject;
    }

    public static JSONObject RPCRequest2(String method, Object param1, Object param2){
        JSONObject jsonObject = RPCRequestUtil.getMainJsonParam(method);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(param1);
        jsonArray.add(param2);
        jsonObject.put("params",jsonArray);
        log.warn("RPCRequest2: "+jsonObject.toJSONString());
        return jsonObject;
    }

    public static JSONObject RPCRequest3(String method, Object param1, Object param2, Object param3){
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(param1);
        jsonArray.add(param2);
        jsonArray.add(param3);
        JSONObject jsonObject = RPCRequestUtil.getMainJsonParam(method);
        jsonObject.put("params",jsonArray);
        return jsonObject;
    }

    public static JSONObject RPCRequest6(String method, Object param1, Object param2, Object param3, Object param4, Object param5, Object param6){
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(param1);
        jsonArray.add(param2);
        jsonArray.add(param3);
        jsonArray.add(param4);
        jsonArray.add(param5);
        jsonArray.add(param6);
        JSONObject jsonObject = RPCRequestUtil.getMainJsonParam(method);
        jsonObject.put("params",jsonArray);
        return jsonObject;
    }
}
