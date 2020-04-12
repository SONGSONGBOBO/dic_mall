package com.songbo.dicshop.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.songbo.dicshop.entity.DsAddr;
import com.songbo.dicshop.entity.DsAuctionInfo;
import com.songbo.dicshop.entity.DsUser;
import com.songbo.dicshop.entity.DsUserInfo;
import com.songbo.dicshop.mapper.DsAuctionInfoMapper;
import com.songbo.dicshop.mapper.DsUserInfoMapper;
import com.songbo.dicshop.mapper.DsUserMapper;
import com.songbo.dicshop.service.DsUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DsUserServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/2/13 下午10:04
 **/
@Service
@Slf4j
public class DsUserServiceImpl extends ServiceImpl<DsUserMapper, DsUser> implements DsUserService {

    @Resource
    private DsUserMapper dsUserMapper;
    @Resource
    private DsUserInfoMapper dsUserInfoMapper;

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
    public DsUser getUserByTel(String tel) {
        return dsUserMapper.getUserByTel(tel);
    }

    @Override
    public DsUser getUserByName(String name) {
        return dsUserMapper.getUserByName(name);
    }

    @Override
    public DsUser getUserById(int id) {
        return dsUserMapper.selectById(id);
    }

    @Override
    public DsUser getUserByCode(String code) {
        return dsUserMapper.getUserByCode(code);
    }

    @Override
    public List<DsUserInfo> getUserInfoByUserId(int id) {
        return dsUserInfoMapper.getUserInfoListByUserId(id);
    }

    @Override
    public DsUserInfo getUserInfoByUserInfoId(int userInfoId) {
        return dsUserInfoMapper.selectById(userInfoId);
    }

    @Override
    public void setUserInfo(DsUserInfo dsUserInfo) {
        dsUserInfoMapper.insert(dsUserInfo);
    }

    @Override
    public void deleteUserInfo(int userInfoId) {
        dsUserInfoMapper.deleteById(userInfoId);
    }

    @Override
    public void updateUserInfoByUserInfoId(DsUserInfo dsUserInfo) {
        //dsUserInfoMapper.updateUserInfo(dsUserInfo.getDsUserInfoAddr(), dsUserInfo.getDsUserInfoCate(), dsUserInfo.getDsUserInfoId());
        dsUserInfoMapper.updateById(dsUserInfo);
    }

    @Override
    public DsAddr getByUserId(int id) {
        return dsUserMapper.getDsaddrByUesrId(id);
    }

    @Override
    public void inviteIntegral(String code, int useId) {

    }


}
