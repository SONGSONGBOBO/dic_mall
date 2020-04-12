package com.songbo.dicshop.bean.auction;

import com.songbo.dicshop.entity.DsAuction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName DsAuctionListForUserResult
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/19 下午4:19
 **/
@Data
@ApiModel(value = "用户拍卖列表", description = "用户拍卖列表")
public class DsAuctionListForUserResult {
    @ApiModelProperty("拍卖实体")
    private DsAuction dsAuction;
    @ApiModelProperty("最高价")
    private double maxPrice;

    @ApiModelProperty("拍卖中标，0拍卖未结束，1拍卖结束已中标，2拍卖结束未中标")
    private int status;

    public DsAuctionListForUserResult() {
    }

    public DsAuctionListForUserResult(DsAuction dsAuction, double maxPrice, int status) {
        this.dsAuction = dsAuction;
        this.status = status;
        this.maxPrice = maxPrice;
    }
}
