package com.songbo.dicshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.songbo.dicshop.entity.DsAddr;
import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.entity.DsUserInfo;


public interface DsUserMapper extends BaseMapper<DsUser> {
    DsUser getUserByName(String name);
    Double getAmountByUserId(int id);
    DsUser getUserByTel(String tel);

    String getAddrByUserId(int userId);

    DsAddr getDsaddrByUesrId(int userId);

    //code
    DsUser getUserByCode(String code);
}