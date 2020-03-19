package com.songbo.dicshop.service.Impl;

import com.songbo.dicshop.entity.DsGoodsInfo;
import com.songbo.dicshop.mapper.DsGoodsInfoMapper;
import com.songbo.dicshop.service.DsGoodsInfoService;
import com.songbo.dicshop.service.DsGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DsGoodsInfoServiceImpl
 * @Description TODO
 * @Author songbo
 * @Date 2020/1/14 下午2:47
 **/
@Service
@Slf4j
public class DsGoodsInfoServiceImpl implements DsGoodsInfoService {

    @Resource
    private DsGoodsInfoMapper dsGoodsInfoMapper;

    @Override
    public List<DsGoodsInfo> getGoodsInfosByPid(int pid) {
        return dsGoodsInfoMapper.getDsGoodsInfosByPid(pid);
    }

    @Override
    public boolean insertGoodsInfo(DsGoodsInfo dsGoodsInfo) {
        try {
            dsGoodsInfoMapper.insert(dsGoodsInfo);
            return true;
        } catch (Exception e) {
            log.error("insert goodsinfo fail: {}", e);
            return false;
        }

    }

    @Override
    public DsGoodsInfo getInfoByName(String name) {
        return dsGoodsInfoMapper.getDsGoodsInfoByName(name);
    }

    @Override
    public DsGoodsInfo getInfoById(int id) {
        return dsGoodsInfoMapper.selectById(id);
    }
}
