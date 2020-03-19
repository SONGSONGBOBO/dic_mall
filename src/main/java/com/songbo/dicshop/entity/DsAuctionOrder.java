package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("拍卖订单")
public class DsAuctionOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ds_auction_order_id", type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionOrderId;
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionOrderNum;
    @ApiModelProperty("拍卖结算价格")
    private Double dsAuctionOrderAuction;
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionOrderAuctionInfoId;
    @ApiModelProperty(hidden = true)
    private Integer dsAuctionOrderUserId;
    @ApiModelProperty(hidden = true)
    //0为待支付,1为已支付
    private Integer dsAuctionOrderStatus;

    public DsAuctionOrder() {}

    public DsAuctionOrder(Integer dsAuctionOrderNum, Double dsAuctionOrderAuction, Integer dsAuctionOrderAuctionInfoId, Integer dsAuctionOrderUserId) {
        this.dsAuctionOrderNum = dsAuctionOrderNum;
        this.dsAuctionOrderAuction = dsAuctionOrderAuction;
        this.dsAuctionOrderAuctionInfoId = dsAuctionOrderAuctionInfoId;
        this.dsAuctionOrderUserId = dsAuctionOrderUserId;
    }
}