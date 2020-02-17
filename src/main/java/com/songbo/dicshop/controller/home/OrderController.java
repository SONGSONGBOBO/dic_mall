package com.songbo.dicshop.controller.home;

import com.songbo.dicshop.entity.DsOrder;
import com.songbo.dicshop.service.DsOrderService;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/13 下午6:10
 **/
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private DsOrderService dsOrderService;

    @PostMapping("/saveOrder")
    public ResultUtil saveOrder(@RequestBody DsOrder dsOrder
                                ) {
        /**System.out.println(cates);*/
        System.out.println(dsOrder);
        return null;
    }
}
