package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.bean.auction.AuctionResult;
import com.songbo.dicshop.entity.*;
import com.songbo.dicshop.exception.AddrException;
import com.songbo.dicshop.mapper.DsAuctionOrderMapper;
import com.songbo.dicshop.service.AuctionService;
import com.songbo.dicshop.service.RedisService;
import com.songbo.dicshop.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;

/**
 * @ClassName AuctionServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/21 下午9:34
 **/
@Service
@Slf4j
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Resource
    private DsAuctionOrderMapper dsAuctionOrderMapper;

    final String AUCTIONKEY = "auction::";
    final String AUCTIONTIMEKEY = "auctionSign::";
    final long auctionTime = 30 * 60 * 1000;

    @Override
    public void setAuction(DsGoodsInfo dsGoodsInfo) throws AddrException {
        String key = AUCTIONTIMEKEY + dsGoodsInfo.getDsGoodsInfoPid() + "::" + dsGoodsInfo.getDsGoodsInfoId();
        try {
            redisService.setValue(key, System.currentTimeMillis()+auctionTime, Duration.ofMillis(auctionTime));
        } catch (Exception e) {
            log.error("设置新的拍卖失败:{}", e);
            throw new AddrException("设置新的拍卖失败！");
        }
    }

    @Override
    public void addAuction(DsGoodsInfo dsGoodsInfo, DsUser dsUser, Double auction) throws Exception {
       /* String key = AUCTIONKEY + dsGoodsInfo.getDsGoodsInfoPid() + "::" + dsGoodsInfo.getDsGoodsInfoId();
        String timeKey = AUCTIONTIMEKEY + dsGoodsInfo.getDsGoodsInfoPid() + "::" + dsGoodsInfo.getDsGoodsInfoId();
        Long dead = (Long) redisService.getValue(timeKey);
        //dead = System.currentTimeMillis() + 10000;
        Long time = System.currentTimeMillis();
        if (transactionService.getAmount(dsUser.getDsUserId()) < auction) {
            throw new AddrException("余额不足！");
        } else if (dead == null || time > dead) {
            throw new AddrException("拍卖时间超时或者服务器异常！");
        } else if (auction < dsGoodsInfo.getDsGoodsInfoAuction()) {
            throw new AddrException("拍卖价格小于初始拍卖价格！");
        } else {
            try {
               *//* transactionService.sendMoney(auction,
                        transactionService.getAddr(dsUser.getDsUserId()),
                        transactionService.getMainAddr());*//*
            } catch (Exception e) {
                log.error("节点服务器错误:{}", e);
                throw new AddrException("节点服务器错误！");
            }

            if (!redisService.setZset(key, new AuctionResult(dsGoodsInfo.getDsGoodsInfoId(),dsUser.getDsUserName(), auction, time).toString(), auction)) {
                log.error("redis存储失败:{}", dsUser.getDsUserName());
                throw new AddrException("redis存储失败");
            }
            //websocket广播
            //simpMessagingTemplate.convertAndSendToUser(String.valueOf(dsGoodsInfo.getDsGoodsInfoId()), "/queue/sendUser", redisService.getZset(key));

            simpMessagingTemplate.convertAndSend("queue/sendAll",  redisService.getZset(key));
        }
*/

    }

    @Override
    public List<AuctionResult> getAuctionResults(int goodsInfoId) {

        return null;
    }

    @Override
    public void closeAuction(DsGoodsInfo dsGoodsInfo, double auction, int userId) {
       /* String keyTime = AUCTIONTIMEKEY + dsGoodsInfo.getDsGoodsInfoPid() + "::" + dsGoodsInfo.getDsGoodsInfoId();
        String key = AUCTIONKEY + dsGoodsInfo.getDsGoodsInfoPid() + "::" + dsGoodsInfo.getDsGoodsInfoId();
        DsAuctionOrder auctionOrder = new DsAuctionOrder(1, auction, dsGoodsInfo.getDsGoodsInfoId(), userId);

        dsAuctionOrderMapper.insert(auctionOrder);
        redisService.deleteKey(keyTime);*/

    }

    @Override
    public List<DsAuction> getListAdmin() {

        return null;
    }
}
