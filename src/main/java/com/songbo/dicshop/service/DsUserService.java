package com.songbo.dicshop.service;

import com.songbo.dicshop.entity.DsUser;

public interface DsUserService {

    boolean saveUser(DsUser dsUser);

    boolean updateUser(int userId);

    DsUser getUserByTel(String tel);
}
