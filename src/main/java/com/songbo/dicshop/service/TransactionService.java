package com.songbo.dicshop.service;

import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.exception.AddrException;

public interface TransactionService {

    String createNewAddr(int userId, double integral) throws Exception;

    String getAddr(int userId) ;

    String getMainAddr();

    Double getAmount(int userId) throws Exception;

    void sendMoney(double send, String from, String to);



    //其他币充值积分
    JSONObject rechaege(int cate, double amount, int userId) throws Exception;

    String getNowHash() throws AddrException;
}
