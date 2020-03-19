package com.songbo.dicshop.controller.home;

import com.songbo.dicshop.utils.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LottryController
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/6 下午9:08
 **/
@RestController
@RequestMapping("/lottery")
public class LotteryController {


    @PostMapping("/buyLottery")
    public ResultUtil buyLottery() {
        return null;
    }

    @PostMapping("/setLottery")
    public ResultUtil setLottery() {
        return null;
    }

    @PostMapping("/getLottery")
    public ResultUtil getLottery() {
        return null;
    }

}
