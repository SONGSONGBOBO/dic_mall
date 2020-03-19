package com.songbo.dicshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songbo.dicshop.entity.DsLottery;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author songbo
 * @since 2020-03-06
 */
public interface DsLotteryService extends IService<DsLottery> {

    void setLottery(DsLottery dsLottery);

    void closeLottery(int lotteryId);

    void addLottery();

}
