package com.songbo.dicshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songbo.dicshop.entity.DsAuctionOrder;

import java.util.List;

public interface DsAuctionOrderService extends IService<DsAuctionOrder> {

    void refreshOrder();

    DsAuctionOrder getByAuctionIdAndUserId(int uid, int aid);


    //quartz
    //监听转账状态并更新
    void autoUpdateAuction();
    //usdt支付
    void usdtPay(int orderId);

    List<DsAuctionOrder> getList();

    List<DsAuctionOrder> getListByUser(int userId);
}
