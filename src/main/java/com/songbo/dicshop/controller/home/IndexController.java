package com.songbo.dicshop.controller.home;

import com.songbo.dicshop.service.DsGoodsService;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.Api;
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
@Slf4j
@Api("test")
public class IndexController {

    @Autowired
    private DsGoodsService dsGoodsService;

    @GetMapping("/index")
    public String getIndex(){
        return "test";
    }
}
