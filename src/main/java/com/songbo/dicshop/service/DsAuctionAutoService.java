package com.songbo.dicshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songbo.dicshop.entity.DsAuctionAuto;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songbo
 * @since 2020-03-22
 */
public interface DsAuctionAutoService extends IService<DsAuctionAuto> {

    void autoAdd();

    List<DsAuctionAuto> getListByStatus(int status);
}
