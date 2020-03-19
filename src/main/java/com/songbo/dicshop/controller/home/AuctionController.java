package com.songbo.dicshop.controller.home;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.songbo.dicshop.bean.auction.AuctionResult;
import com.songbo.dicshop.bean.auction.DsAuctionByUser;
import com.songbo.dicshop.bean.auction.DsAuctionResult;
import com.songbo.dicshop.config.SocketManager;

import com.songbo.dicshop.entity.DsAuction;
import com.songbo.dicshop.entity.DsAuctionInfo;
import com.songbo.dicshop.entity.DsGoodsInfo;
import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.exception.AddrException;
import com.songbo.dicshop.service.*;
import com.songbo.dicshop.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

/**
 * @ClassName AuctionController
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/19 下午3:47
 **/
@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private DsGoodsInfoService dsGoodsInfoService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private DsUserService dsUserService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private DsAuctionInfoService dsAuctionInfoService;
    @Autowired
    private DsAuctionService dsAuctionService;

    @PostMapping("/addAuction")
    @ApiOperation(value = "拍卖竞价,会判断是否过期，拍卖价格小于当前最高价或者预设最低价，会返回当前最高价")
    public ResultUtil addAuction(@RequestParam("auction") @ApiParam("拍卖价格") double auction,
                                 @RequestParam("auctionId") @ApiParam("拍卖id") int auctionId,
                                 @RequestHeader("userId") @ApiParam("请求头中的userId") int userId) {
        Long time = System.currentTimeMillis();
        String now = String.valueOf(time);
        Double maxNow = dsAuctionInfoService.getMaxNow(auctionId);
        DsAuction one = dsAuctionService.getOne(auctionId);
        double min = one.getDsAuctionMinadd();
        double max = one.getDsAuctionMaxadd();
        double init = one.getDsAuctionPrice();
        if (time > Long.parseLong(one.getDsAuctionEnd())) {
            return ResultUtil.result400("拍卖已过期", one.getDsAuctionEnd());
        }
        if (maxNow == null) {
            maxNow = init;
        }
        if (auction<maxNow+min) {
            return ResultUtil.result400("拍卖价过低", maxNow);
        }
        if (auction>maxNow + max) {
            return ResultUtil.result400("拍卖加价过高", maxNow);
        }
        try {
            //扣除积分
        } catch (Exception e) {
            return ResultUtil.result500("扣除积分失败", e.getMessage());
        }
        try {
            dsAuctionInfoService.addAuction(new DsAuctionInfo(userId, auction,now,auctionId));
            return ResultUtil.result200("success", null);
        } catch (Exception e) {
            return ResultUtil.result400("fail", e.getMessage());
        }
    }

    @PostMapping("/getAuctionHistory")
    @ApiOperation(value = "获取竞价列表")
    public ResultUtil getAuction(@RequestParam("auctionId") @ApiParam("拍卖id") int auctionId) {

        try {
            List<DsAuctionResult> listByAuctionId = dsAuctionInfoService.getListByAuctionId(auctionId);
            return ResultUtil.result200("success", listByAuctionId);
        } catch (Exception e) {
            return ResultUtil.result400("fail", e.getMessage());
        }
    }
    @PostMapping("/getAuctionById/{auctionId}")
    @ApiOperation(value = "用户获取拍卖详情")
    public ResultUtil getAuctionById(@RequestParam("auctionId") @ApiParam("拍卖id") int auctionId) {

        try {
            DsAuction dsAuction = dsAuctionService.getAuctionByIdAndStatus(auctionId, 1);
            if (dsAuction == null) {
                return ResultUtil.result400("拍卖已关闭", null);
            }
            Double maxNow = dsAuctionInfoService.getMaxNow(dsAuction.getDsAuctionId());
            DsAuctionByUser dsAuctionByUser = new DsAuctionByUser(dsAuction.getDsAuctionId(),dsAuction.getDsAuctionPrice(),dsAuction.getDsAuctionMinadd(),dsAuction.getDsAuctionMaxadd(),dsAuction.getDsAuctionIntegral(),dsAuction.getDsAuctionStart(),dsAuction.getDsAuctionEnd(),dsAuction.getDsAuctionGoodsId(),maxNow,dsAuction.getDsAuctionStatus());
            return ResultUtil.result200("success", dsAuctionByUser);
        } catch (Exception e) {
            return ResultUtil.result400("fail", e.getMessage());
        }
    }

    @GetMapping("/getAuction")
    @ApiOperation(value = "用户获取拍卖列表")
    public ResultUtil getAuctionList() {

        try {
            List<DsAuction> listByStatus = dsAuctionService.getListByStatus(1);
            List<DsAuctionByUser> list = new ArrayList<>();

            for (DsAuction dsAuction : listByStatus) {
                Double maxNow = dsAuctionInfoService.getMaxNow(dsAuction.getDsAuctionId());
                DsAuctionByUser dsAuctionByUser = new DsAuctionByUser(dsAuction.getDsAuctionId(),dsAuction.getDsAuctionPrice(),dsAuction.getDsAuctionMinadd(),dsAuction.getDsAuctionMaxadd(),dsAuction.getDsAuctionIntegral(),dsAuction.getDsAuctionStart(),dsAuction.getDsAuctionEnd(),dsAuction.getDsAuctionGoodsId(),maxNow,dsAuction.getDsAuctionStatus());
                list.add(dsAuctionByUser);
            }


            return ResultUtil.result200("success", list);
        } catch (Exception e) {
            return ResultUtil.result400("fail", e.getMessage());
        }
    }

    @GetMapping("/getAuctionByUserId")
    @ApiOperation(value = "用户参与的拍卖列表")
    public ResultUtil getAuctionByUserId(@RequestHeader("userId") @ApiParam("userid") int userId) {

        try {
            List<DsAuction> dsAuction = dsAuctionService.getListByUserId(userId);

            //Double maxNow = dsAuctionInfoService.getMaxNow(dsAuction.getDsAuctionId());
            //DsAuctionByUser dsAuctionByUser = new DsAuctionByUser(dsAuction.getDsAuctionId(),dsAuction.getDsAuctionPrice(),dsAuction.getDsAuctionMinadd(),dsAuction.getDsAuctionMaxadd(),dsAuction.getDsAuctionIntegral(),dsAuction.getDsAuctionStart(),dsAuction.getDsAuctionEnd(),dsAuction.getDsAuctionGoodsId(),maxNow,dsAuction.getDsAuctionStatus());
            return ResultUtil.result200("success", dsAuction);
        } catch (Exception e) {
            return ResultUtil.result400("fail", e.getMessage());
        }
    }

   /* @PostMapping("/addAuction")
    @ApiOperation(value = "拍卖竞价")
    public ResultUtil addAuction(@RequestParam("auction") @ApiParam("拍卖价格") double auction,
                                 @RequestParam("goodsInfoId") @ApiParam("商品info id") int goodsInfoId,
                                 @RequestHeader("userId") @ApiParam("请求头中的userId") int userId) {

        try {
            auctionService.addAuction(dsGoodsInfoService.getInfoById(goodsInfoId),
                    dsUserService.getUserById(userId), auction);

            return ResultUtil.result200("success", null);
        } catch (Exception e) {
            return ResultUtil.result400("fail", e.getMessage());
        }
    }

    final String AUCTIONKEY = "auction::";

    @RequestMapping("/getAuction")
    @ApiOperation(value = "初始化获取拍卖价格列表，添加拍卖后使用websocket广播")
    public ResultUtil getAuction(@RequestParam("goodsInfoId") @ApiParam("商品info id") Integer goodsInfoId) {
        String key = AUCTIONKEY + dsGoodsInfoService.getInfoById(goodsInfoId).getDsGoodsInfoPid() + "::" + goodsInfoId;
        Set<AuctionResult> zset = redisService.getZset(key);
        return ResultUtil.result200("success", zset);
        //simpMessagingTemplate.convertAndSendToUser(goodsInfoId, "/queue/sendUser", dsUser);

        //simpMessagingTemplate.convertAndSend( "/topic/sendToAll", dsUser);
    }*/

    /*@MessageMapping("/topic/sendToAll")
    @SendTo("/topic/sendToAll")
    public String sendToAll(String message) {
        return message;
    }*/




}
