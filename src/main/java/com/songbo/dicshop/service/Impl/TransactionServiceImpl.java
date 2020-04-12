package com.songbo.dicshop.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.bean.chainResult.CurrentResult;
import com.songbo.dicshop.bean.coinpayments.CommonResponse;
import com.songbo.dicshop.bean.coinpayments.CreateRequest;
import com.songbo.dicshop.entity.DsAddr;
import com.songbo.dicshop.entity.DsAddrInfo;
import com.songbo.dicshop.entity.DsOption;
import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.exception.AddrException;
import com.songbo.dicshop.mapper.DsAddrMapper;
import com.songbo.dicshop.mapper.DsOptionMapper;
import com.songbo.dicshop.mapper.DsUserMapper;
import com.songbo.dicshop.service.*;
import com.songbo.dicshop.utils.CommonUtil;
import com.songbo.dicshop.utils.RPCRequestUtil;
import com.songbo.dicshop.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import org.brunocvcunha.coinpayments.model.CreateTransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TransactionServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/24 下午2:28
 **/
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    @Resource
    private DsUserMapper dsUserMapper;
    @Resource
    private DsAddrMapper dsAddrMapper;
    @Autowired
    private RPCRequestService rpcRequestService;
    @Resource
    private DsOptionMapper dsOptionMapper;
    @Autowired
    private CoinpaymentsService coinpaymentsService;
    @Autowired
    private DsAddrInfoService dsAddrInfoService;

    private static Map<String, String> map = new HashMap<>();
    private static Headers ukexHeaders = null;
    static {
        map.put("Content-Type", "application/x-www-form-urlencoded");
        ukexHeaders= Headers.of(map);
    }

    @Override
    public String createNewAddr(int userId, double integral) throws AddrException {
        String addrByUserId = dsUserMapper.getAddrByUserId(userId);
        if (addrByUserId != null) {
            return addrByUserId;
        }
        JSONObject jsonObject = RPCRequestUtil.getMainJsonParam(CommonUtil.CREATE_ADDRESS);
        CurrentResult<String, String> createAddressResult = rpcRequestService.CurrentRPCRequest(jsonObject, String.class, String.class);
        if (createAddressResult != null && createAddressResult.getError()==null){
            dsAddrMapper.insert(new DsAddr(createAddressResult.getResult(), integral, userId));
            return createAddressResult.getResult();
        } else {
            log.error("创建地址失败： "+createAddressResult.toString());
            throw new AddrException("创建地址失败");
        }
    }

    @Override
    public String getAddr(int userId)  {

            return dsUserMapper.getAddrByUserId(userId);


    }

    @Override
    public String getMainAddr() {
        return null;
    }

    //'{ "addresses" : ["RPdQE6K9YEdXkKzPXEqN1gEqSqzeUVYogv","RPdQE6K9YEdXkKzPXEqN1gEqSqzeUVYogv"] }'
    @Override
    public Double getAmount(int userId) throws Exception {
        JSONObject j1 = new JSONObject();
        JSONArray array = new JSONArray();
        String addr = dsUserMapper.getAddrByUserId(userId);
        array.add(addr);
        j1.put("addresses",array);
        JSONObject jsonObject = RPCRequestUtil.RPCRequest1(CommonUtil.GET_BALANCE,j1);
        CurrentResult<JSONObject, String> createAddressResult = rpcRequestService.CurrentRPCRequest(jsonObject, JSONObject.class, String.class);
        if (createAddressResult != null && createAddressResult.getError()==null){
            //dsAddrMapper.insert(new DsAddr(createAddressResult.getResult(),userId));
            JSONObject jsonObject1 = createAddressResult.getResult();
            return Double.parseDouble(String.valueOf(jsonObject1.get("balance"))) /100000000;
        } else {
            log.error("获取余额失败： "+createAddressResult.toString());
            throw new Exception("获取余额失败");
        }
    }

    @Override
    public void sendMoney(double send, String from, String to) {

    }


    @Override
    public JSONObject rechaege(int cate, double amount, int userId) throws Exception {
        String time = String.valueOf(System.currentTimeMillis());
       /* DsOption option = dsOptionMapper.getOption();
        Double rate = option.getDsOptionUsdtRate();*/
        DsUser user = dsUserMapper.selectById(userId);
        Double rate = null;
        try {
            JSONObject ukex = rpcRequestService.currentRequest("https://api.ukex.io/api/index/ticker/dic_usdt", "get", ukexHeaders, null, JSONObject.class);
            BigDecimal last = (BigDecimal) ukex.get("last");
            rate = last.doubleValue();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        double usdt = amount * rate;
        if (usdt <= 1) {
            throw new Exception("充值金额过少");
        }
        try {
            CreateTransactionResponse transaction = coinpaymentsService.createTransaction(new CreateRequest(CommonUtil.COINPAYMENTS_CREATE_TRANSACTION,
                    usdt, "USDT.ERC20", "USDT.ERC20", user.getDsUserMail() == null ? CommonUtil.COINPAYMENTS_MAIL : user.getDsUserMail(),
                    CommonUtil.COINPAYMENTS_ADDRESS, user.getDsUserName()));
            JSONObject jsonObject = new JSONObject();
            dsAddrInfoService.save(new DsAddrInfo( transaction.getTransactionId(), usdt, amount, 0, 1, userId, time, time));
            jsonObject.put("addr", transaction.getAddress());
            jsonObject.put("amount", transaction.getAmount());
            return jsonObject;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

                   /* CreatePaymentResult createPaymentResult = paymentsService.ceatePayment(
                            new CreatePaymentRequest(dic,"usd",  dic, "usdterc20", "", String.valueOf(userId),"recharge_usdterc20"+dic)
                    );*/

    }

    //getbestblockhash
    @Override
    public String getNowHash() throws AddrException {
        JSONObject jsonObject = RPCRequestUtil.getMainJsonParam("getbestblockhash");
        CurrentResult<String, String> result = rpcRequestService.CurrentRPCRequest(jsonObject, String.class, String.class);
        if (result != null && result.getError()==null){
            return result.getResult();
        } else {
            throw new AddrException("获取hash失败！");
        }
    }
}
