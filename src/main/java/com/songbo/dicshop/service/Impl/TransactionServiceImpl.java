package com.songbo.dicshop.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.bean.chainResult.CurrentResult;
import com.songbo.dicshop.entity.DsAddr;
import com.songbo.dicshop.exception.AddrException;
import com.songbo.dicshop.mapper.DsAddrMapper;
import com.songbo.dicshop.mapper.DsUserMapper;
import com.songbo.dicshop.service.DsUserService;
import com.songbo.dicshop.service.RPCRequestService;
import com.songbo.dicshop.service.TransactionService;
import com.songbo.dicshop.utils.CommonUtil;
import com.songbo.dicshop.utils.RPCRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    @Override
    public String createNewAddr(int userId) throws AddrException {
        String addrByUserId = dsUserMapper.getAddrByUserId(userId);
        if (addrByUserId != null) {
            return addrByUserId;
        }
        JSONObject jsonObject = RPCRequestUtil.getMainJsonParam(CommonUtil.CREATE_ADDRESS);
        CurrentResult<String, String> createAddressResult = rpcRequestService.CurrentRPCRequest(jsonObject, String.class, String.class);
        if (createAddressResult != null && createAddressResult.getError()==null){
            dsAddrMapper.insert(new DsAddr(createAddressResult.getResult(),userId));
            return createAddressResult.getResult();
        } else {
            log.error("创建地址失败： "+createAddressResult.toString());
            throw new AddrException("创建地址失败");
        }
    }

    @Override
    public String getAddr(int userId) throws AddrException {
        try {
            return dsUserMapper.getAddrByUserId(userId);
        } catch (Exception e){
            throw new AddrException("当前用户无地址，请联系管理员");
        }

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
}
