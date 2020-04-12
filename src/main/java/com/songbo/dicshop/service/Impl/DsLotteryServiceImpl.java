package com.songbo.dicshop.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songbo.dicshop.entity.DsAuctionAuto;
import com.songbo.dicshop.entity.DsLottery;
import com.songbo.dicshop.entity.DsLotteryInfo;
import com.songbo.dicshop.entity.DsLotteryResult;
import com.songbo.dicshop.mapper.DsAuctionAutoMapper;
import com.songbo.dicshop.mapper.DsLotteryInfoMapper;
import com.songbo.dicshop.mapper.DsLotteryMapper;
import com.songbo.dicshop.mapper.DsLotteryResultMapper;
import com.songbo.dicshop.service.DsLotteryService;
import com.songbo.dicshop.service.TransactionService;
import com.songbo.dicshop.utils.RandomNum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName DsLotteryServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/4/10 下午3:18
 **/
@Service
@Slf4j
public class DsLotteryServiceImpl extends ServiceImpl<DsLotteryMapper, DsLottery>  implements DsLotteryService {

    @Resource
    private DsLotteryMapper dsLotteryMapper;
    @Resource
    private DsLotteryInfoMapper dsLotteryInfoMapper;
    @Resource
    private DsLotteryResultMapper  dsLotteryResultMapper;
    @Autowired
    private TransactionService transactionService;



    @Override
    public void closeLottery() {
        long now = System.currentTimeMillis();
        List<DsLottery> refresh = dsLotteryMapper.getListByStatus(0);
        for (DsLottery dsLottery : refresh) {
            if (Long.parseLong(dsLottery.getDsLotteryEnd()) < now) {
                try {
                    //1.get hash
                    String nowHash = transactionService.getNowHash();
                    char end = nowHash.toCharArray()[nowHash.length()-1];
                    int seeds = end;
                    if (Character.isDigit(end)){
                        seeds -= '0';
                    }
                    //2.insert l result
                    List<Integer> rewardsList = new ArrayList<>();
                    int total = 0;
                    JSONObject rewards = JSONObject.parseObject(dsLottery.getDsLotteryRewards());
                    for (int i = 1; i <= rewards.size(); i++) {
                        JSONObject levelInfo = rewards.getJSONObject(String.valueOf(i));
                        int count = (int) levelInfo.get("count");
                        rewardsList.add(count);
                        total += count;
                    }
                    List<DsLotteryInfo> lotteryInfos = dsLotteryInfoMapper.getListByStatusAndLotteryId(0, dsLottery.getDsLotteryId());
                    List<Integer> randomList = RandomNum.getRandomList(lotteryInfos.size(), total, seeds);
                    JSONObject rewardsResult = new JSONObject();

                    //3.update l info status
                    for (int j = 1; j <= rewardsList.size(); j++) {
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i < rewardsList.get(j-1); i++) {
                            jsonArray.add(randomList.get(i));
                            DsLotteryInfo dsLotteryInfo = lotteryInfos.get(randomList.get(i));
                            randomList.remove(i);
                            dsLotteryInfo.setDsLotteryInfoStatus(j);
                        }
                        rewardsResult.put(String.valueOf(j), jsonArray);
                    }
                    //insert l result
                    dsLotteryResultMapper.insert(new DsLotteryResult(nowHash, rewardsResult.toJSONString(), dsLottery.getDsLotteryId()));

                    //4.update l status
                    dsLottery.setDsLotteryStatus(1);
                    dsLotteryMapper.updateById(dsLottery);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("奖票结算失败", e.getMessage());
                }
            }
        }

    }


}
