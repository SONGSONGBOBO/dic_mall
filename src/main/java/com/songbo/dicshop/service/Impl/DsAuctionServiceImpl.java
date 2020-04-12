package com.songbo.dicshop.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.songbo.dicshop.bean.auction.AuctionResult;
import com.songbo.dicshop.bean.auction.DsAuctionByUser;
import com.songbo.dicshop.bean.coinpayments.CreateRequest;
import com.songbo.dicshop.bean.coinpayments.GetTxInfoRequest;
import com.songbo.dicshop.bean.payments.CreatePaymentRequest;
import com.songbo.dicshop.bean.payments.CreatePaymentResult;
import com.songbo.dicshop.bean.payments.PaymentStatusResult;
import com.songbo.dicshop.entity.DsAuction;
import com.songbo.dicshop.entity.DsAuctionInfo;
import com.songbo.dicshop.entity.DsAuctionOrder;
import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.enums.OrderEnum;
import com.songbo.dicshop.mapper.DsAuctionInfoMapper;
import com.songbo.dicshop.mapper.DsAuctionMapper;
import com.songbo.dicshop.mapper.DsAuctionOrderMapper;
import com.songbo.dicshop.service.CoinpaymentsService;
import com.songbo.dicshop.service.DsAuctionService;
import com.songbo.dicshop.service.DsUserService;
import com.songbo.dicshop.service.PaymentsService;
import com.songbo.dicshop.utils.CommonUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.brunocvcunha.coinpayments.model.CreateTransactionResponse;
import org.brunocvcunha.coinpayments.model.TransactionDetailsResponse;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songbo
 * @since 2020-03-16
 */
@Service
@Slf4j
public class DsAuctionServiceImpl extends ServiceImpl<DsAuctionMapper, DsAuction> implements DsAuctionService {

    @Resource
    private DsAuctionMapper dsAuctionMapper;

    @Resource
    private DsAuctionInfoMapper dsAuctionInfoMapper;
    @Resource
    private DsAuctionOrderMapper dsAuctionOrderMapper;
    @Autowired
    private PaymentsService paymentsService;
    @Autowired
    private CoinpaymentsService coinpaymentsService;
    @Autowired
    private DsUserService dsUserService;

    @Override
    public void setAuction(DsAuction dsAuction) {

        dsAuctionMapper.insert(dsAuction);
    }

    @Override
    public void deleteAuction(int id) {
        dsAuctionMapper.deleteById(id);
    }

    @Override
    public void updateAuction(DsAuction dsAuction) {
        dsAuctionMapper.updateById(dsAuction);
    }

    @Override
    public List<DsAuction> getListByAuctionIdAndStatus(int auctionId, int status) {
        return null;
    }

    @Override
    public List<DsAuctionByUser> getListByIndex(int auctionId) {
        return null;
    }

    @Override
    public List<DsAuction> getListByStatus(int status) {
        return dsAuctionMapper.getListByStatus(status);
    }

    @Override
    public DsAuction getOne(int id) {
        return dsAuctionMapper.selectById(id);
    }

    @Override
    public DsAuction getAuctionByIdAndStatus(int id, int status) {
        return dsAuctionMapper.getByIdAndStatus(id, status);
    }

    @Override
    public List<DsAuction> getListByUserId(int userId) {
        return dsAuctionMapper.getListByUserId(userId);
    }

    @Override
    public List<DsAuction> getList() {
        return dsAuctionMapper.selectList(null);
    }

    @Override
    public void publish() {
        List<DsAuction> listByStatus = dsAuctionMapper.getListByStatus(0);
        long now = System.currentTimeMillis();

        for (DsAuction dsAuction : listByStatus) {
            if (Long.parseLong(dsAuction.getDsAuctionStart()) < now) {
                try {
                    dsAuction.setDsAuctionStatus(1);
                    dsAuctionMapper.updateById(dsAuction);
                    log.info("定时发布拍卖",dsAuction);
                } catch (Exception e) {
                    log.error("定时发布拍卖 fail",e);
                }

            }
        }
    }

    @Override
    public void close() {
        List<DsAuction> listByStatus = dsAuctionMapper.getListByStatus(1);
        long now = System.currentTimeMillis();

        for (DsAuction dsAuction : listByStatus) {
            if (Long.parseLong(dsAuction.getDsAuctionEnd()) < now) {
                DsAuctionInfo dsAuctionInfo = dsAuctionInfoMapper.getByPriceMax(dsAuction.getDsAuctionId());
                if (dsAuctionInfo != null) {
                   /* CreatePaymentResult result = paymentsService.ceatePayment(new CreatePaymentRequest(
                            dsAuctionInfo.getDsAuctionInfoPrice(), "usd", dsAuctionInfo.getDsAuctionInfoPrice(), "usdterc20", "",
                            String.valueOf(dsAuctionInfo.getDsAuctionInfoId()), String.valueOf(dsAuctionInfo.getDsAuctionInfoUserId())
                    ));*/
                    DsUser user = dsUserService.getUserById(dsAuctionInfo.getDsAuctionInfoUserId());
                    try {
                        CreateTransactionResponse transaction = coinpaymentsService.createTransaction(new CreateRequest(CommonUtil.COINPAYMENTS_CREATE_TRANSACTION,
                                dsAuctionInfo.getDsAuctionInfoPrice(), "USDT.ERC20", "USDT.ERC20", user.getDsUserMail() == null ? CommonUtil.COINPAYMENTS_MAIL : user.getDsUserMail(),
                                CommonUtil.COINPAYMENTS_ADDRESS, user.getDsUserName()));
                        DsAuctionOrder order = new DsAuctionOrder(1,dsAuctionInfo.getDsAuctionInfoPrice(),dsAuctionInfo.getDsAuctionInfoId(),
                                dsAuctionInfo.getDsAuctionInfoUserId(), 0, transaction.getTransactionId(), String.valueOf(now), String.valueOf(now), transaction.getAddress(), 1);
                        dsAuctionOrderMapper.insert(order);
                    } catch (Exception e) {
                        log.error("定时结算拍卖 fail",e);
                    }

                }
                dsAuction.setDsAuctionStatus(2);
                dsAuctionMapper.updateById(dsAuction);
                log.info("定时结算拍卖",dsAuction);
            }
        }
    }

    @Override
    public void refreshOrder() {
        List<DsAuctionOrder> listByRefresh = dsAuctionOrderMapper.getListByRefresh();
        for (DsAuctionOrder dsAuctionOrder : listByRefresh) {
            try {
                TransactionDetailsResponse txInfo = coinpaymentsService.getTxInfo(new GetTxInfoRequest(CommonUtil.COINPAYMENTS_GET_TX_INFO, dsAuctionOrder.getDsAuctionOrderPaymentId()));
                int status = txInfo.getStatus();
                if (dsAuctionOrder.getDsAuctionOrderStatus() != status) {
                    dsAuctionOrder.setDsAuctionOrderStatus(status);
                    dsAuctionOrderMapper.updateById(dsAuctionOrder);
                }
            } catch (Exception e) {
                log.error("refreshOrder fail:"+dsAuctionOrder.getDsAuctionOrderId(), e);
            }

        }
    }


}
