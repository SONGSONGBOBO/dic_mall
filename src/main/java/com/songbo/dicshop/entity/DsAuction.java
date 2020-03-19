package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author songbo
 * @since 2020-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("拍卖")
public class DsAuction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ds_auction_id", type = IdType.AUTO)
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
    @ApiModelProperty(hidden = true)
    //0为刚添加，1为ok，2为关闭
    private Integer dsAuctionStatus;

    public DsAuction() {
    }

    public DsAuction(Double dsAuctionPrice, Double dsAuctionMinadd, Double dsAuctionMaxadd, Double dsAuctionIntegral, String dsAuctionStart, String dsAuctionEnd, Integer dsAuctionGoodsId, Integer dsAuctionStatus) {
        this.dsAuctionPrice = dsAuctionPrice;
        this.dsAuctionMinadd = dsAuctionMinadd;
        this.dsAuctionMaxadd = dsAuctionMaxadd;
        this.dsAuctionIntegral = dsAuctionIntegral;
        this.dsAuctionStart = dsAuctionStart;
        this.dsAuctionEnd = dsAuctionEnd;
        this.dsAuctionGoodsId = dsAuctionGoodsId;
        this.dsAuctionStatus = dsAuctionStatus;
    }
}
