package com.songbo.dicshop.quartz;

import com.songbo.dicshop.entity.DsAddrInfo;
import com.songbo.dicshop.mapper.DsAddrMapper;
import com.songbo.dicshop.service.DsAddrInfoService;
import com.songbo.dicshop.service.DsAuctionService;
import com.songbo.dicshop.service.IntegralService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName IntegralQuartz
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/22 下午1:45
 **/
@Slf4j
public class IntegralQuartz implements Job {

    @Autowired
    private IntegralService integralService;
    @Resource
    private DsAddrMapper dsAddrMapper;
    @Autowired
    private DsAddrInfoService dsAddrInfoService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        integralService.refreshIntegral(dsAddrMapper.selectList(null));
        //更新usdt充值
        List<DsAddrInfo> needRefresh = dsAddrInfoService.getListByNeedRefresh();
        if (needRefresh == null) {
            log.info("充值无需更新 ");
            return;
        }
        integralService.refreshIntegralForOthers(needRefresh);
        log.info("定时更新正常 ");


    }
}
