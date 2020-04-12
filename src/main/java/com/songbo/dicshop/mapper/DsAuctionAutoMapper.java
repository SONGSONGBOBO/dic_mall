package com.songbo.dicshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.dicshop.entity.DsAuctionAuto;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songbo
 * @since 2020-03-22
 */
public interface DsAuctionAutoMapper extends BaseMapper<DsAuctionAuto> {
    List<DsAuctionAuto> getListByStatus(int status);
}
