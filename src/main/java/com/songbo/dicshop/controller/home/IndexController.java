package com.songbo.dicshop.controller.home;

import com.songbo.dicshop.service.DsGoodsService;
import com.songbo.dicshop.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author songbo
 * @Date 2020/1/14 下午2:14
 **/
@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @Autowired
    private DsGoodsService dsGoodsService;

    @GetMapping()
    public ResultUtil getIndex(){
        return null;
    }
}
