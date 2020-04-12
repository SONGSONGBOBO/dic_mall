package com.songbo.dicshop.controller.admin;

import com.songbo.dicshop.entity.DsLottery;
import com.songbo.dicshop.service.DsLotteryService;
import com.songbo.dicshop.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName BackLotteryController
 * @Description TODO
 * @Author songbo
 * @Date 2020/4/10 下午3:17
 **/
@RestController
@RequestMapping("/admin")
public class BackLotteryController {

    @Autowired
    private DsLotteryService dsLotteryService;

    @PostMapping("/setLottery")
    public ResultUtil setLottery(@RequestBody DsLottery dsLottery) {
        try {
            dsLotteryService.save(dsLottery);
            return ResultUtil.result200("添加成功！", dsLottery);
        } catch (Exception e) {
            return ResultUtil.result500("服务器错误！", e.getMessage());
        }
    }
}
