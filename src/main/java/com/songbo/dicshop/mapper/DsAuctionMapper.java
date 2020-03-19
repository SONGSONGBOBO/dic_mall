package com.songbo.dicshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.dicshop.bean.auction.DsAuctionResult;
import com.songbo.dicshop.entity.DsAuction;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songbo
 * @since 2020-03-15
 */
public interface DsAuctionMapper extends BaseMapper<DsAuction> {
    List<DsAuction> getListByStatus(int status);

    DsAuction getByIdAndStatus(int id, int status);

    List<DsAuction> getListByUserId(int id);
}
