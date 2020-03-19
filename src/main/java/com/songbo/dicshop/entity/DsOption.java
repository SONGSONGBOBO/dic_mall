package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName DsOpntion
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/11 下午12:32
 **/

@Data
@TableName("ds_option")
@ApiModel("网站设置")
public class DsOption {

    @TableId(value = "ds_option_id", type = IdType.AUTO)
    private Integer dsOptionId;

    @ApiModelProperty(value = "轮播图")
    private String dsOptionImgs;
    @ApiModelProperty(hidden = true)
    private String dsOptionStatus;
}
