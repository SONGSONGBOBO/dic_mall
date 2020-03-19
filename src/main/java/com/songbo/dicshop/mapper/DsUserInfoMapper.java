package com.songbo.dicshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.dicshop.entity.DsUserInfo;

import java.util.List;


public interface DsUserInfoMapper extends BaseMapper<DsUserInfo> {

    List<DsUserInfo> getUserInfoListByUserId(int userId);

    void updateUserInfo(String addr, int cate, int id);
}