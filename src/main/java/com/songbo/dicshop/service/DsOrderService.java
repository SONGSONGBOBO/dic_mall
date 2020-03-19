package com.songbo.dicshop.service;

import com.songbo.dicshop.entity.DsOrder;

import java.util.List;

public interface DsOrderService {

    DsOrder getOrderById(int id);

    List<DsOrder> getOdersByUserId(int userId);

    /**
     *@Description TODO 订单创建
     *@param
     *@return
     **/
    boolean saveOrder(DsOrder dsOrder);
    /**
     *@Description TODO 更新订单状态:已完成
     *@param
     *@return
     **/
    boolean updateOrderCompleted(DsOrder dsOrder);
    /**
     *@Description TODO 更新订单状态:已关闭
     *@param
     *@return
     **/
    boolean updateOrderClosed(DsOrder dsOrder);

}
