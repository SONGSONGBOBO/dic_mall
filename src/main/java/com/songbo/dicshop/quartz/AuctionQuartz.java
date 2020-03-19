package com.songbo.dicshop.quartz;

import com.songbo.dicshop.service.DsAuctionService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @ClassName AuctionQuartz
 * @Description TODO
 * @Author songbo
 * @Date 2020/3/16 下午1:30
 **/
@Slf4j
public class AuctionQuartz implements Job {

    @Autowired
    private DsAuctionService dsAuctionService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            dsAuctionService.publish();
            log.info("定时发布正常 ");
        } catch (Exception e) {
         log.error("定时发布失败",e);
        }
        try {
            dsAuctionService.close();
            log.info("定时结算正常 ");
        } catch (Exception e) {
            log.error("定时结算失败",e);
        }
    }
}
