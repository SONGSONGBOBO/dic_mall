package com.songbo.dicshop.bean.entityResults;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName cartsResult
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/19 下午11:32
 **/
@Data
@ApiModel(value = "购物车返回", description = "cartsResult")
public class CartsResult {
    @ApiModelProperty(hidden = true)
    private Integer dsCartId;
    @ApiModelProperty(value = "商品数量")
    private Integer dsCartNum;
    @ApiModelProperty(value = "商品状态，0为缺货，1为正常")
    private Integer dsCartStatus;
    @ApiModelProperty(value = "添加时间")
    private String  dsCartTime;
}
