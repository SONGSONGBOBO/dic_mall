package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.entity.DsOrder;
import com.songbo.dicshop.service.DsOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DsOrderServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/13 下午6:18
 **/
@Service
public class DsOrderServiceImpl implements DsOrderService {


    @Override
    public DsOrder getOrderById(int id) {
        return null;
    }

    @Override
    public DsOrder getByUserIdAndAuctionId(int uid, int aid) {
        return null;
    }

    @Override
    public List<DsOrder> getOdersByUserId(int userId) {
        return null;
    }

    @Override
    public boolean saveOrder(DsOrder dsOrder) {
        return false;
    }

    @Override
    public boolean updateOrderCompleted(DsOrder dsOrder) {
        return false;
    }

    @Override
    public boolean updateOrderClosed(DsOrder dsOrder) {
        return false;
    }


}
