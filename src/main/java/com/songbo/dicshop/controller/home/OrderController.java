package com.songbo.dicshop.controller.home;

import com.songbo.dicshop.entity.DsGoodsInfo;
import com.songbo.dicshop.entity.DsOrder;
import com.songbo.dicshop.service.DsOrderService;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("商品订单接口，非拍卖订单")
public class OrderController {
    @Autowired
    private DsOrderService dsOrderService;

    @PostMapping("/addOrderByGoodInfo")
    @ApiOperation("从商品页创建订单")
    public ResultUtil addOrderByGoodInfo(@RequestParam("infoId") @ApiParam("商品infoid") int infoId,
                                         @RequestParam("num") @ApiParam("商品数量") int num,
                                         @RequestHeader("userId") @ApiParam("请求头中的userId") int userId
                                ) {
        /**System.out.println(carts);*/
        System.out.println(infoId);
        return ResultUtil.result200("success", num);
    }

    @PostMapping("/addOrderByCart")
    @ApiOperation("从购物车创建订单")
    public ResultUtil addOrderByCart(@RequestBody List<Integer> cartIdList,
                                     @RequestHeader("userId") @ApiParam("请求头中的userId") int userId) {
        /**System.out.println(carts);*/
        System.out.println(cartIdList);
        return ResultUtil.result200("success", cartIdList);
    }

    @PostMapping("/updateOrder")
    @ApiOperation("结算订单")
    public ResultUtil updateOrder(@RequestParam("orderId") @ApiParam("订单id") int orderId,
                                  @RequestHeader("userId") @ApiParam("请求头中的userId") int userId) {
        /**System.out.println(carts);*/
        System.out.println(orderId);
        return ResultUtil.result200("success", orderId);
    }


}
