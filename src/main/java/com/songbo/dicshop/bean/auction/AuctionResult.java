package com.songbo.dicshop.bean.auction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName AuctionResult
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/20 下午5:10
 **/
@Data
@ApiModel(value = "拍卖返回", description = "AuctionResult")
public class AuctionResult implements Serializable {
    @ApiModelProperty("拍卖商品详情id")
    private int goodsInfoId;
    @ApiModelProperty("username")
    private String userName;
    @ApiModelProperty("拍卖价格")
    private Double auction;
    @ApiModelProperty("发起时间")
    private Long createTime;


    public AuctionResult(){}

    public AuctionResult(int goodsInfoId, String userName, Double auction, Long createTime) {
        this.goodsInfoId = goodsInfoId;
        this.userName = userName;
        this.auction = auction;
        this.createTime = createTime;
    }




}
