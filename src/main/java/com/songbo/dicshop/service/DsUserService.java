package com.songbo.dicshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.songbo.dicshop.entity.DsAddr;
import com.songbo.dicshop.entity.DsAuctionInfo;
import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.entity.DsUserInfo;

import java.util.List;

public interface DsUserService extends IService<DsUser> {

    boolean saveUser(DsUser dsUser);


    DsUser getUserByTel(String tel);

    DsUser getUserByName(String name);

    DsUser getUserById(int id);

    DsUser getUserByCode(String code);

    //userinfo
    List<DsUserInfo> getUserInfoByUserId(int id);

    DsUserInfo getUserInfoByUserInfoId(int userInfoId);

    void setUserInfo(DsUserInfo dsUserInfo);

    void deleteUserInfo(int userInfoId);

    void updateUserInfoByUserInfoId(DsUserInfo dsUserInfo);

    DsAddr getByUserId(int id);

    //邀请码得积分
    void inviteIntegral(String code, int useId);
}
