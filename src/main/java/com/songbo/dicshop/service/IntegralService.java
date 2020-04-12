package com.songbo.dicshop.service;

import com.songbo.dicshop.entity.DsAddr;
import com.songbo.dicshop.entity.DsAddrInfo;

import java.util.List;

public interface IntegralService {

    //刷新积分
    void refreshIntegral(List<DsAddr> addrs);

    void refreshIntegralForOthers(List<DsAddrInfo> infos);

}
