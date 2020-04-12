package com.songbo.dicshop.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.songbo.dicshop.entity.DsAuctionAuto;
import com.songbo.dicshop.mapper.DsAuctionAutoMapper;
import com.songbo.dicshop.service.DsAuctionAutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author songbo
 * @since 2020-03-22
 */
@Service
@Slf4j
public class DsAuctionAutoServiceImpl extends ServiceImpl<DsAuctionAutoMapper, DsAuctionAuto> implements DsAuctionAutoService {

    @Resource
    private DsAuctionAutoMapper dsAuctionAutoMapper;
    @Override
    public void autoAdd() {

    }

    @Override
    public List<DsAuctionAuto> getListByStatus(int status) {
        return dsAuctionAutoMapper.getListByStatus(status);
    }
}
