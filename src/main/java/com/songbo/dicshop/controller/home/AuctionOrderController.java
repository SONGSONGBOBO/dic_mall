package com.songbo.dicshop.controller.home;

import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.entity.DsAuctionOrder;
import com.songbo.dicshop.entity.DsGoods;
import com.songbo.dicshop.mapper.DsAuctionOrderMapper;
import com.songbo.dicshop.service.DsAuctionInfoService;
import com.songbo.dicshop.service.DsAuctionOrderService;
import com.songbo.dicshop.service.DsGoodsService;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName AuctionOrderController
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/20 下午12:37
 **/
@RestController
@RequestMapping("/auctionOrder")
@Api("拍卖订单和支付")
public class AuctionOrderController {

    @Resource
    private DsAuctionOrderMapper dsAuctionOrderMapper;

    @Autowired
    private DsAuctionOrderService dsAuctionOrderService;
    @Autowired
    private DsAuctionInfoService dsAuctionInfoService;
    @Autowired
    private DsGoodsService dsGoodsService;

    @PostMapping("/getOdersByUser")
    @ApiOperation("根据拍卖id获取支付订单")
    public ResultUtil getOdersByUser(@RequestHeader("userId") @ApiParam("请求头中userId") int userId,
                                     @RequestParam("auctionId") @ApiParam("拍卖id") int auctionId) {
        DsAuctionOrder order = dsAuctionOrderService.getByAuctionIdAndUserId(userId, auctionId);
        Integer auctionInfoId = order.getDsAuctionOrderAuctionInfoId();
        DsGoods dsGoods = dsGoodsService.getByAuctionInfoService(auctionInfoId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("goodsName", dsGoods.getDsGoodsName());
        jsonObject.put("price", order.getDsAuctionOrderAuction());
        jsonObject.put("addr", order.getDsAuctionOrderAddr());
        return ResultUtil.result200("success", jsonObject);
    }

   /* @PostMapping("/pay")
    @ApiOperation("获取支付地址等")
    public ResultUtil pay(@RequestParam("orderId") @ApiParam("订单id") int orderId) {
        return ResultUtil.result200("", dsAuctionOrderService.getById(orderId));
    }*/

}
