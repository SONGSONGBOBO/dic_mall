package com.songbo.dicshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songbo.dicshop.bean.auction.AuctionResult;
import com.songbo.dicshop.bean.auction.DsAuctionByUser;
import com.songbo.dicshop.entity.DsAuction;
import org.quartz.SchedulerException;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songbo
 * @since 2020-03-16
 */
public interface DsAuctionService extends IService<DsAuction> {
    /**
      *@Description TODO 创建拍卖mysql版本
      *@param
      *@return
    **/
    void setAuction(DsAuction dsAuction) ;

    void deleteAuction(int id);

    void updateAuction(DsAuction dsAuction);

    List<DsAuction> getListByAuctionIdAndStatus(int auctionId, int status);

    List<DsAuctionByUser> getListByIndex(int auctionId);

    DsAuction getOne(int id);

    DsAuction getAuctionByIdAndStatus(int id, int status);

    List<DsAuction> getListByUserId(int userId);
    //admin
    List<DsAuction> getList();
    List<DsAuction> getListByStatus(int status);
    /*quartz*/
    void publish();
    void close();
    void refreshOrder();

}
