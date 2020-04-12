package com.songbo.dicshop.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.songbo.dicshop.bean.coinpayments.CommonResponse;
import com.songbo.dicshop.bean.coinpayments.GetTxInfoRequest;
import com.songbo.dicshop.entity.DsAddrInfo;
import com.songbo.dicshop.enums.OrderEnum;
import com.songbo.dicshop.mapper.DsAddrInfoMapper;
import com.songbo.dicshop.service.CoinpaymentsService;
import com.songbo.dicshop.service.DsAddrInfoService;
import org.brunocvcunha.coinpayments.model.TransactionDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 充值历史 服务实现类
 * </p>
 *
 * @author songbo
 * @since 2020-03-23
 */
@Service
public class DsAddrInfoServiceImpl extends ServiceImpl<DsAddrInfoMapper, DsAddrInfo> implements DsAddrInfoService {

    @Resource
    private DsAddrInfoMapper dsAddrInfoMapper;
    @Autowired
    private CoinpaymentsService coinpaymentsService;

    @Override
    public List<DsAddrInfo> getListByNeedRefresh() {
        return dsAddrInfoMapper.getListByNeedRefresh();
    }

    /*
    * {"error":"ok","result":{"time_created":1585819968,"time_expires":1585827168,"status":100,"status_text":"Complete","type":"coins","coin":"USDT.ERC20","amount":200000000,"amountf":"2.00000000","received":200000000,"receivedf":"2.00000000","recv_confirms":3,"payment_address":"0x376cf71d4e5eb24bdadef8a0daf85bb814ae290b","time_completed":1585823194,"send_tx":"0x125fe6111715d5b65162cc788459614ac76f694544fcdea5fd4ff3dbd7540964"}}*/
    @Override
    public void refresh() {
        List<DsAddrInfo> refresh = dsAddrInfoMapper.getListByNeedRefresh();
        String time = String.valueOf(System.currentTimeMillis());
        for (DsAddrInfo dsAddrInfo : refresh) {
            try {
                TransactionDetailsResponse transactionDetailsResponse = coinpaymentsService.getTxInfo(new GetTxInfoRequest("get_tx_info", dsAddrInfo.getDsAddrInfoTxid()));
                int value = transactionDetailsResponse.getStatus();
                if (value != dsAddrInfo.getDsAddrInfoStatus()){
                    dsAddrInfo.setDsAddrInfoStatus(value);
                    dsAddrInfo.setDsAddrInfoUpdateTime(time);
                    dsAddrInfoMapper.updateById(dsAddrInfo);
                }
            } catch (Exception e) {
                log.error("refresh coinpaymentsService.getTxInfo fail: {}",e);
            }
            //int value = OrderEnum.valueOf(jsonObject.get("status")).getValue();


        }
    }
}
