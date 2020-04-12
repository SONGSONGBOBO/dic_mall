package com.songbo.dicshop.controller.home;

import com.alibaba.fastjson.JSONArray;
import com.songbo.dicshop.entity.DsLottery;
import com.songbo.dicshop.entity.DsLotteryInfo;
import com.songbo.dicshop.service.DsLotteryInfoService;
import com.songbo.dicshop.service.DsLotteryService;
import com.songbo.dicshop.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LottryController
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/6 下午9:08
 **/
@RestController
@RequestMapping("/lottery")
public class LotteryController {

    @Autowired
    private DsLotteryService dsLotteryService;
    @Autowired
    private DsLotteryInfoService dsLotteryInfoService;


    @PostMapping("/buyLottery")
    public ResultUtil buyLottery(@RequestHeader("userId") int userId,
                                 @RequestBody DsLotteryInfo dsLotteryInfo) {
        Long time =System.currentTimeMillis();
        DsLottery lottery = dsLotteryService.getById(dsLotteryInfo.getDsLotteryInfoLotteryId());
        if (Long.parseLong(lottery.getDsLotteryEnd()) < time) {
            return ResultUtil.result400("当前奖票已过期", lottery.getDsLotteryEnd());
        }

        DsLottery dsLottery = dsLotteryService.getById(dsLotteryInfo.getDsLotteryInfoLotteryId());
        List<Integer> list = JSONArray.parseArray(dsLotteryInfo.getDsLotteryInfoNumber(), Integer.class);
        dsLotteryInfo.setDsLotteryInfoPrice(dsLottery.getDsLotteryPrice() * list.size());
        dsLotteryInfo.setDsLotteryInfoStatus(0);

        dsLotteryInfo.setDsLotteryInfoLotteryTime(String.valueOf(time));
        dsLotteryInfo.setDsLotteryInfoUserId(userId);
        try {
            dsLotteryInfoService.save(dsLotteryInfo);
            return ResultUtil.result200("购买成功！", null);
        } catch (Exception e) {
            return ResultUtil.result500("购买失败！", e.getMessage());
        }
    }


    @PostMapping("/getLotteryList")
    public ResultUtil getLottery(@RequestParam("lotteryId") int lotteryId) {
        //dsLotteryService.
        return null;
    }

}
