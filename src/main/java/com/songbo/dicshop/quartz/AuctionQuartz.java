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
        dsAuctionService.publish();

        dsAuctionService.close();

        dsAuctionService.refreshOrder();

    }
}
