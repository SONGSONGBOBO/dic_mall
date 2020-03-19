package com.songbo.dicshop.bean.auction;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName DsAuctionByUser
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/18 下午6:12
 **/
@Data
public class DsAuctionByUser {

    @ApiModelProperty(hidden = true)
    private Integer dsAuctionId;
    @ApiModelProperty("初始价格")
    private Double dsAuctionPrice;
    @ApiModelProperty("最小加价")
    private Double dsAuctionMinadd;
    @ApiModelProperty("最大加价")
    private Double dsAuctionMaxadd;
    @ApiModelProperty("消耗积分")
    private Double dsAuctionIntegral;
    @ApiModelProperty("开始时间，时间戳")
    private String dsAuctionStart;
    @ApiModelProperty("结束时间，时间戳")
    private String dsAuctionEnd;
    @ApiModelProperty("商品id")
    private Integer dsAuctionGoodsId;
    @ApiModelProperty("最高出价")
    private Double dsAuctionMaxPrice;
    @ApiModelProperty(hidden = true)
    //0为刚添加，1为ok，2为关闭
    private Integer dsAuctionStatus;

    public DsAuctionByUser() {
    }

    public DsAuctionByUser(Integer dsAuctionId, Double dsAuctionPrice, Double dsAuctionMinadd, Double dsAuctionMaxadd, Double dsAuctionIntegral, String dsAuctionStart, String dsAuctionEnd, Integer dsAuctionGoodsId, Double dsAuctionMaxPrice, Integer dsAuctionStatus) {
        this.dsAuctionId = dsAuctionId;
        this.dsAuctionPrice = dsAuctionPrice;
        this.dsAuctionMinadd = dsAuctionMinadd;
        this.dsAuctionMaxadd = dsAuctionMaxadd;
        this.dsAuctionIntegral = dsAuctionIntegral;
        this.dsAuctionStart = dsAuctionStart;
        this.dsAuctionEnd = dsAuctionEnd;
        this.dsAuctionGoodsId = dsAuctionGoodsId;
        this.dsAuctionMaxPrice = dsAuctionMaxPrice;
        this.dsAuctionStatus = dsAuctionStatus;
    }
}
