package com.songbo.dicshop.service;

import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.exception.AddrException;

public interface TransactionService {

    String createNewAddr(int userId) throws Exception;

    String getAddr(int userId) throws AddrException;

    String getMainAddr();

    Double getAmount(int userId) throws Exception;

    void sendMoney(double send, String from, String to);
}
