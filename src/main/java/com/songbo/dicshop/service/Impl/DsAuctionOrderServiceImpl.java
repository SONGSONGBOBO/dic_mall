package com.songbo.dicshop.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songbo.dicshop.entity.DsAuctionAuto;
import com.songbo.dicshop.entity.DsAuctionOrder;
import com.songbo.dicshop.mapper.DsAuctionAutoMapper;
import com.songbo.dicshop.mapper.DsAuctionOrderMapper;
import com.songbo.dicshop.service.DsAuctionOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DsAuctionOrderServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/20 下午1:54
 **/
@Service
@Slf4j
public class DsAuctionOrderServiceImpl extends ServiceImpl<DsAuctionOrderMapper, DsAuctionOrder> implements DsAuctionOrderService {

    @Resource
    private DsAuctionOrderMapper dsAuctionOrderMapper;

    @Override
    public void refreshOrder() {
        //List<>
    }

    @Override
    public DsAuctionOrder getByAuctionIdAndUserId(int uid, int aid) {
        return dsAuctionOrderMapper.getByUsrIdAndAuctionId(uid, aid);
    }

    @Override
    public void autoUpdateAuction() {

    }

    @Override
    public void usdtPay(int orderId) {

    }

    @Override
    public List<DsAuctionOrder> getList() {
        return dsAuctionOrderMapper.selectList(null);
    }

    @Override
    public List<DsAuctionOrder> getListByUser(int userId) {
        return dsAuctionOrderMapper.getListByUser(userId);
    }
}
