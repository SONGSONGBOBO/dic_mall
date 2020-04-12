package com.songbo.dicshop.controller.admin;

import com.songbo.dicshop.entity.DsAuctionOrder;
import com.songbo.dicshop.service.DsAuctionOrderService;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName BackAuctionOrderController
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/20 下午4:09
 **/
@RestController
@RequestMapping("/admin")
@Api("后台拍卖订单处理")
public class BackAuctionOrderController {

    @Autowired
    private DsAuctionOrderService dsAuctionOrderService;

    @PostMapping("getOrderList")
    @ApiOperation("获取全部订单列表")
    public ResultUtil getOrderList() {
        try {
            List<DsAuctionOrder> list = dsAuctionOrderService.getList();
            return ResultUtil.result200("查询成功", list);
        } catch (Exception e) {
            return ResultUtil.result500("查询失败", e.getMessage());

        }
    }
}
