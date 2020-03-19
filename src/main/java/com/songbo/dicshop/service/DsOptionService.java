package com.songbo.dicshop.service;

import com.songbo.dicshop.mapper.DsOptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName DsOptionService
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/11 下午12:36
 **/
@Service
@Slf4j
public class DsOptionService {

    @Resource
    private DsOptionMapper dsOptionMapper;

    public String getImgs() {
        return dsOptionMapper.getOption().getDsOptionImgs();
    }
}
