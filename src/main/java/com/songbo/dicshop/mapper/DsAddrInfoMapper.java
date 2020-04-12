package com.songbo.dicshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.dicshop.entity.DsAddrInfo;

import java.util.List;


/**
 * <p>
 * 充值历史 Mapper 接口
 * </p>
 *
 * @author songbo
 * @since 2020-03-23
 */
public interface DsAddrInfoMapper extends BaseMapper<DsAddrInfo> {

    List<DsAddrInfo> getListByNeedRefresh();
}
