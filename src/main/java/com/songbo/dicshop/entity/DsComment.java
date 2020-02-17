package com.songbo.dicshop.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("ds_comment")
public class DsComment {

    @TableId("ds_comment_id")
    private Integer dsCommentId;

    @TableField("ds_comment_user_id")
    private Integer dsCommentUserId;

    @TableField("ds_comment_goods_id")
    private Integer dsCommentGoodsId;

    @TableField("ds_comment_info")
    private String dsCommentInfo;

}