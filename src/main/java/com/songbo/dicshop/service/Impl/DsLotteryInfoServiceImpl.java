package com.songbo.dicshop.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songbo.dicshop.entity.DsLottery;
import com.songbo.dicshop.entity.DsLotteryInfo;
import com.songbo.dicshop.mapper.DsLotteryInfoMapper;
import com.songbo.dicshop.mapper.DsLotteryMapper;
import com.songbo.dicshop.service.DsLotteryInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName DsLotteryInfoServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/4/10 下午9:31
 **/
@Service
@Slf4j
public class DsLotteryInfoServiceImpl extends ServiceImpl<DsLotteryInfoMapper, DsLotteryInfo> implements DsLotteryInfoService {
}
