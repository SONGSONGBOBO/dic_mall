package com.songbo.dicshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.dicshop.entity.DsLotteryInfo;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author songbo
 * @since 2020-03-11
 */
public interface DsLotteryInfoMapper extends BaseMapper<DsLotteryInfo> {
    List<DsLotteryInfo> getListByStatusAndLotteryId(int status, int id);
}
