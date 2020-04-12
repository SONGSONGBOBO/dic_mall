package com.songbo.dicshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songbo.dicshop.entity.DsAddrInfo;

import java.util.List;


/**
 * <p>
 * 充值历史 服务类
 * </p>
 *
 * @author songbo
 * @since 2020-03-23
 */
public interface DsAddrInfoService extends IService<DsAddrInfo> {

    /*获取除了已确认的*/
    List<DsAddrInfo> getListByNeedRefresh();

    void refresh() throws Exception;
}
