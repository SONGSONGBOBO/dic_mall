package com.songbo.dicshop.quartz;

import com.songbo.dicshop.entity.DsAuctionAuto;
import com.songbo.dicshop.entity.DsAuctionInfo;
import com.songbo.dicshop.service.DsAuctionAutoService;
import com.songbo.dicshop.service.DsAuctionInfoService;
import com.songbo.dicshop.service.DsAuctionService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName AutoAuctionQuartz
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/30 上午11:57
 **/
@Slf4j
public class AutoAuctionQuartz implements Job {

    @Autowired
    private DsAuctionAutoService dsAuctionAutoService;
    @Autowired
    private DsAuctionService dsAuctionService;
    @Autowired
    private DsAuctionInfoService dsAuctionInfoService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<DsAuctionAuto> list = dsAuctionAutoService.getListByStatus(1);
        String time = null;
        for (DsAuctionAuto dsAuctionAuto : list) {
            time = String.valueOf(System.currentTimeMillis());
            try {
                dsAuctionInfoService.addAuction(new DsAuctionInfo(dsAuctionAuto.getDsAuctionAutoUserId(),
                        dsAuctionAuto.getDsAuctionAutoAdd()+dsAuctionInfoService.getMaxNow(dsAuctionAuto.getDsAuctionAutoAuctionId()),
                        time,dsAuctionAuto.getDsAuctionAutoAuctionId()));
            } catch (Exception e) {
                log.error("AutoAuctionQuartz: {}"+dsAuctionAuto.getDsAuctionAutoUserId(),e.getMessage());
            }
        }
    }
}
