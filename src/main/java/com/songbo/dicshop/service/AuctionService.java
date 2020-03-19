package com.songbo.dicshop.service;

import com.songbo.dicshop.bean.auction.AuctionResult;
import com.songbo.dicshop.entity.DsAuction;
import com.songbo.dicshop.entity.DsGoodsInfo;
import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.exception.AddrException;

import java.util.List;

public interface AuctionService {

    /**
      *@Description TODO 发起auction
      *@param
      *@return
    **/
    void setAuction(DsGoodsInfo dsGoodsInfo)throws AddrException;


    /**
      *@Description TODO 添加auction
      *@param
      *@return
    **/
    void addAuction(DsGoodsInfo dsGoodsInfo, DsUser dsUser, Double auction) throws Exception;
    /**
      *@Description TODO
      *@param
      *@return
    **/
    List<AuctionResult> getAuctionResults(int goodsInfoId);
    /**
      *@Description TODO
      *@param
      *@return
    **/
    void closeAuction(DsGoodsInfo dsGoodsInfo, double auction, int userId);

    //admin
    List<DsAuction> getListAdmin();

}
