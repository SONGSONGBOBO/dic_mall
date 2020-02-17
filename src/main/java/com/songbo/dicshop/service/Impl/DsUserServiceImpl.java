package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.mapper.DsUserMapper;
import com.songbo.dicshop.service.DsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName DsUserServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/13 下午10:04
 **/
@Service
@Slf4j
public class DsUserServiceImpl implements DsUserService {

    @Autowired
    private DsUserMapper dsUserMapper;

    @Override
    public boolean saveUser(DsUser dsUser) {
        try {
            dsUserMapper.insert(dsUser);
            return true;
        } catch (Exception e) {
            log.error("insert user fail", e);
            return false;
        }

    }

    @Override
    public boolean updateUser(int userId) {
        return false;
    }

    @Override
    public DsUser getUserByTel(String tel) {
        return null;
    }
}
