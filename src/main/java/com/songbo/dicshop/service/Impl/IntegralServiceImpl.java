package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.bean.coinpayments.GetTxInfoRequest;
import com.songbo.dicshop.bean.payments.PaymentStatusResult;
import com.songbo.dicshop.entity.DsAddr;
import com.songbo.dicshop.entity.DsAddrInfo;
import com.songbo.dicshop.enums.OrderEnum;
import com.songbo.dicshop.mapper.DsAddrMapper;
import com.songbo.dicshop.service.*;
import com.songbo.dicshop.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.brunocvcunha.coinpayments.model.TransactionDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName IntegralServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/19 下午7:41
 **/
@Slf4j
@Service
public class IntegralServiceImpl implements IntegralService {

    @Resource
    private DsAddrMapper dsAddrMapper;
    @Resource
    private TransactionService transactionService;

    @Autowired
    private DsAddrInfoService dsAddrInfoService;
    @Autowired
    private PaymentsService paymentsService;
    @Autowired
    private CoinpaymentsService coinpaymentsService;

    @Override
    public void refreshIntegral(List<DsAddr> addrs)  {
        for (DsAddr addr : addrs) {

            double amount = addr.getDsAddrAmount();
            double balance = addr.getDsAddrBalance();
            Double chainAmount = null;
            try {
                chainAmount = transactionService.getAmount(addr.getDsAddrUserId());
                if (amount < chainAmount) {

                    addr.setDsAddrAmount(chainAmount);
                    addr.setDsAddrBalance(balance+chainAmount-amount);
                    dsAddrMapper.updateById(addr);
                    log.info("更新"+addr.getDsAddrUserId()+"余额");

                }
            } catch (Exception e) {
                log.error("refreshIntegral fail {}",e);
            }

        }
    }

    @Override
    public void refreshIntegralForOthers(List<DsAddrInfo> infos) {
        for (DsAddrInfo dsAddrInfo : infos) {
            try {
                TransactionDetailsResponse txInfo = coinpaymentsService.getTxInfo(new GetTxInfoRequest(CommonUtil.COINPAYMENTS_GET_TX_INFO, dsAddrInfo.getDsAddrInfoTxid()));
                int status = txInfo.getStatus();
                if (status != dsAddrInfo.getDsAddrInfoStatus()) {
                    //refresh
                    DsAddr dsAddr = dsAddrMapper.getByUserId(dsAddrInfo.getDsAddrInfoUserId());
                    dsAddr.setDsAddrBalance(dsAddr.getDsAddrBalance() + dsAddrInfo.getDsAddrInfoIntegral());
                    dsAddrMapper.updateById(dsAddr);
                }
            } catch (Exception e) {
                log.error("refreshIntegralForOthers fail {}",e);
            }

        }
    }
}
