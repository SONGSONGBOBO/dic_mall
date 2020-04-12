package com.songbo.dicshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songbo.dicshop.bean.auction.AuctionResult;
import com.songbo.dicshop.bean.auction.DsAuctionResult;
import com.songbo.dicshop.entity.DsAuction;
import com.songbo.dicshop.entity.DsAuctionInfo;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songbo
 * @since 2020-03-16
 */
public interface DsAuctionInfoService extends IService<DsAuctionInfo> {
    /**
     *@Description TODO 添加拍卖
     *@param
     *@return
     **/
    void addAuction(DsAuctionInfo dsAuctionInfo) throws Exception;

    List<DsAuctionResult> getListByAuctionId(int id);

    /**
      *@Description TODO 获取当前最高
      *@param
      *@return
    **/
    Double getMaxNow(int auctionInfoId);

    List<DsAuctionResult> getListByAuctionIdAndUserId(int aid, int uid);


}
