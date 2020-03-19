package com.songbo.dicshop.controller.admin;

import com.songbo.dicshop.entity.DsAuction;
import com.songbo.dicshop.exception.AddrException;
import com.songbo.dicshop.service.*;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName BackAuctionController
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/7 下午8:29
 **/
@RestController
@RequestMapping("/admin/auction")
@Api(value = "拍卖-后台权限")
public class BackAuctionController {

    @Autowired
    private DsGoodsInfoService dsGoodsInfoService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private DsUserService dsUserService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private DsAuctionService dsAuctionService;

    @PostMapping("/setAuction")
    @ApiOperation(value = "拍卖发起")
    public ResultUtil setAuction(@RequestBody DsAuction dsAuction) {
        if (Long.parseLong(dsAuction.getDsAuctionEnd()) <= Long.parseLong(dsAuction.getDsAuctionStart())){
            return ResultUtil.result400("end need > start", null);
        }
        try {
            dsAuction.setDsAuctionStatus(0);
            dsAuctionService.setAuction(dsAuction);
            return ResultUtil.result200("success", null);
        } catch (Exception e) {
            return ResultUtil.result400("fail", e.getMessage());
        }
    }

    @GetMapping("/getAuctionList")
    @ApiOperation(value = "拍卖列表")
    public ResultUtil getAuction() {

        try {
            List<DsAuction> list = dsAuctionService.getList();
            return ResultUtil.result200("success", list);
        } catch (Exception e) {
            return ResultUtil.result400("fail", e.getMessage());
        }
    }

    @GetMapping("/deleteAuction/{id}")
    @ApiOperation(value = "delete拍卖")
    public ResultUtil deleteAuction(@PathVariable @ApiParam("拍卖id") int id) {

        try {
            dsAuctionService.deleteAuction(id);
            return ResultUtil.result200("success", id);
        } catch (Exception e) {
            return ResultUtil.result400("fail", e.getMessage());
        }
    }

    @PostMapping("/updateAuction/{id}")
    @ApiOperation(value = "更新拍卖")
    public ResultUtil updateAuction(@RequestBody DsAuction dsAuction,@PathVariable("id") @ApiParam("拍卖id") int id) {

        try {
            dsAuction.setDsAuctionId(id);
            if (Long.parseLong(dsAuction.getDsAuctionEnd()) <= Long.parseLong(dsAuction.getDsAuctionStart())){
                return ResultUtil.result400("end need > start", null);
            }
            dsAuctionService.updateAuction(dsAuction);
            return ResultUtil.result200("success", dsAuction.getDsAuctionId());
        } catch (Exception e) {
            return ResultUtil.result400("fail", e.getMessage());
        }
    }
   /* @PostMapping("/setAuction")
    @ApiOperation(value = "拍卖发起")
    public ResultUtil setAuction(@RequestParam("auction") @ApiParam("拍卖价格") double auction,
                                 @RequestParam("goodsInfoId") @ApiParam("商品info id") int goodsInfoId,
                                 @RequestHeader("userId") @ApiParam("请求头中的userId") int userId) {

        try {
            auctionService.setAuction(dsGoodsInfoService.getInfoById(goodsInfoId));
            return ResultUtil.result200("success", null);
        } catch (AddrException e) {
            return ResultUtil.result400("fail", e.getMessage());
        }
    }*/
}
