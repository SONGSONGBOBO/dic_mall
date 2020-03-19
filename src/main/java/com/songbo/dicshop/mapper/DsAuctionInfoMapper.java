package com.songbo.dicshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.dicshop.bean.auction.DsAuctionResult;
import com.songbo.dicshop.entity.DsAuctionInfo;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songbo
 * @since 2020-03-16
 */
public interface DsAuctionInfoMapper extends BaseMapper<DsAuctionInfo> {
    DsAuctionInfo getListByAid(int aid);
    List<DsAuctionResult> getDsAuctionResultListByAid(int aid);
    Double getMaxPrice(int aid);

    //最高
    DsAuctionInfo getByPriceMax(int auctionId);
}
