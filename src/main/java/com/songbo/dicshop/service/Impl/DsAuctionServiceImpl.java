package com.songbo.dicshop.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.songbo.dicshop.bean.auction.AuctionResult;
import com.songbo.dicshop.bean.auction.DsAuctionByUser;
import com.songbo.dicshop.entity.DsAuction;
import com.songbo.dicshop.entity.DsAuctionInfo;
import com.songbo.dicshop.entity.DsAuctionOrder;
import com.songbo.dicshop.mapper.DsAuctionInfoMapper;
import com.songbo.dicshop.mapper.DsAuctionMapper;
import com.songbo.dicshop.mapper.DsAuctionOrderMapper;
import com.songbo.dicshop.service.DsAuctionService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
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
                dsAuction.setDsAuctionStatus(1);
                dsAuctionMapper.updateById(dsAuction);
                log.info("定时发布拍卖",dsAuction);
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
                    DsAuctionOrder order = new DsAuctionOrder(1,dsAuctionInfo.getDsAuctionInfoPrice(),dsAuctionInfo.getDsAuctionInfoId(),dsAuctionInfo.getDsAuctionInfoUserId());
                    dsAuctionOrderMapper.insert(order);
                }
                dsAuction.setDsAuctionStatus(2);
                dsAuctionMapper.updateById(dsAuction);
                log.info("定时结算拍卖",dsAuction);
            }
        }
    }


}
