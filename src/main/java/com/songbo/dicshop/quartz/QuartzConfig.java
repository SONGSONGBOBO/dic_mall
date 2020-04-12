package com.songbo.dicshop.quartz;



import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName QuartzConfig
 * @Description TODO
 * @Author songbo
 * @Date 19-8-26 下午5:21
 **/
@Configuration
public class QuartzConfig {

    private static final String LIKE_TASK_IDENTITY = "AuctionQuartz";

    private static final String INTEGRAL_TASK_IDENTITY = "IntegralQuartz";

    private static final String RECHARGE_TASK_IDENTITY = "RechargeQuartz";
    private static final String AUTO_TASK_IDENTITY = "AutoAuctionQuartz";

    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(AuctionQuartz.class).withIdentity(LIKE_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public JobDetail integralQuartzDetail(){
        return JobBuilder.newJob(IntegralQuartz.class).withIdentity(INTEGRAL_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public JobDetail rechargeQuartzDetail(){
        return JobBuilder.newJob(RechargeQuartz.class).withIdentity(RECHARGE_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public JobDetail autoAuctionQuartzDetail(){
        return JobBuilder.newJob(AutoAuctionQuartz.class).withIdentity(AUTO_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger autoAuctionQuartzTrigger(){

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(70)  //设置时间周期单位秒
                //.withIntervalInHours(2)  //两个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(autoAuctionQuartzDetail())
                .withIdentity(AUTO_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
    @Bean
    public Trigger quartzTrigger(){

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(60)  //设置时间周期单位秒
                //.withIntervalInHours(2)  //两个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(LIKE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger integralQuartzTrigger(){

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(200)  //设置时间周期单位秒
                //.withIntervalInHours(2)  //两个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(integralQuartzDetail())
                .withIdentity(INTEGRAL_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger rechargeTrigger(){

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(150)  //设置时间周期单位秒
                //.withIntervalInHours(2)  //两个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(rechargeQuartzDetail())
                .withIdentity(RECHARGE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }


}


