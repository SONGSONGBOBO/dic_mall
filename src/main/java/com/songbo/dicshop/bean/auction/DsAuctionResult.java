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
@ApiModel(value = "竞价信息返回", description = "竞价信息返回")
public class DsAuctionResult implements Serializable {
    @ApiModelProperty("拍卖历史记录id")
    private int dsAuctionInfoId;
    @ApiModelProperty("username")
    private String dsUserName;
    @ApiModelProperty("拍卖价格")
    private Double dsAuctionInfoPrice;

    @ApiModelProperty("发起时间")
    private Long dsAuctionInfoTime;


    public DsAuctionResult(){}


    public DsAuctionResult(String dsUserName, Double dsAuctionInfoPrice, Long dsAuctionInfoTime) {
        this.dsUserName = dsUserName;
        this.dsAuctionInfoPrice = dsAuctionInfoPrice;
        this.dsAuctionInfoTime = dsAuctionInfoTime;
    }
}
