package com.songbo.dicshop.service;

import com.songbo.dicshop.entity.DsAddr;
import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.entity.DsUserInfo;

import java.util.List;

public interface DsUserService {

    boolean saveUser(DsUser dsUser);

    boolean updateUser(int userId);

    DsUser getUserByTel(String tel);

    DsUser getUserByName(String name);

    DsUser getUserById(int id);

    //userinfo
    List<DsUserInfo> getUserInfoByUserId(int id);

    DsUserInfo getUserInfoByUserInfoId(int userInfoId);

    void setUserInfo(DsUserInfo dsUserInfo);

    void deleteUserInfo(int userInfoId);

    void updateUserInfoByUserInfoId(DsUserInfo dsUserInfo);

    DsAddr getByUserId(int id);
}
