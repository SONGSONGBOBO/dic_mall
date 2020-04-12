package com.songbo.dicshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.dicshop.entity.DsAuctionOrder;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songbo
 * @since 2020-03-03
 */
public interface DsAuctionOrderMapper extends BaseMapper<DsAuctionOrder> {

    DsAuctionOrder getByUsrIdAndAuctionId(int uid, int aid);

    List<DsAuctionOrder> getListByRefresh();

    List<DsAuctionOrder> getListByUser(int userId);
}
