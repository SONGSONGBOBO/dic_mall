package com.songbo.dicshop.quartz;

import com.songbo.dicshop.bean.coinpayments.CommonResponse;
import com.songbo.dicshop.bean.coinpayments.GetTxInfoRequest;
import com.songbo.dicshop.bean.payments.PaymentStatusResult;
import com.songbo.dicshop.entity.DsAddrInfo;
import com.songbo.dicshop.enums.OrderEnum;
import com.songbo.dicshop.service.CoinpaymentsService;
import com.songbo.dicshop.service.DsAddrInfoService;
import com.songbo.dicshop.service.PaymentsService;
import com.songbo.dicshop.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName RechargeQuartz
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/25 下午5:25
 **/
@Slf4j
public class RechargeQuartz implements Job {

    @Autowired
    private DsAddrInfoService dsAddrInfoService;
    @Autowired
    private PaymentsService paymentsService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        try {
            /*for (DsAddrInfo dsAddrInfo : refresh) {
                PaymentStatusResult paymentStatus = paymentsService.getPaymentStatus(dsAddrInfo.getDsAddrInfoTxid());
                int value = OrderEnum.valueOf(paymentStatus.getPayment_status()).getValue();
                if (value != dsAddrInfo.getDsAddrInfoStatus()){
                    dsAddrInfo.setDsAddrInfoStatus(value);
                    dsAddrInfo.setDsAddrInfoUpdateTime(time);
                    dsAddrInfoService.updateById(dsAddrInfo);
                }

            }*/
            dsAddrInfoService.refresh();
        } catch (Exception e) {
            log.error("自动充值更新失败", e.getMessage());
        }
    }
}
