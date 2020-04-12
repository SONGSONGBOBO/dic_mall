package com.songbo.dicshop.service;


import com.songbo.dicshop.bean.coinpayments.CreateRequest;
import com.songbo.dicshop.bean.coinpayments.GetTxInfoRequest;
import org.brunocvcunha.coinpayments.model.CreateTransactionResponse;
import org.brunocvcunha.coinpayments.model.TransactionDetailsResponse;

public interface CoinpaymentsService {

    CreateTransactionResponse createTransaction(CreateRequest createRequest) throws Exception;

    TransactionDetailsResponse getTxInfo(GetTxInfoRequest getTxInfoRequest) throws Exception;
}
