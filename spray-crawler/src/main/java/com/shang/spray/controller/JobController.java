package com.shang.spray.controller;

import com.github.pagehelper.PageInfo;
import com.shang.spray.entity.JobAndTrigger;
import com.shang.spray.job.BaseJob;
import com.shang.spray.service.JobAndTriggerService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/job")
public class JobController {
    @Resource
    private JobAndTriggerService jobAndTriggerService;

    @Resource
    @Qualifier("schedulerFactory")
    private SchedulerFactoryBean schedulerFactoryBean;


    @PostMapping(value = "/addjob")
    public void addjob(@RequestParam(value = "jobName") String jobName,
                       @RequestParam(value = "jobClassName") String jobClassName,
                       @RequestParam(value = "jobGroupName") String jobGroupName,
                       @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        addJob(jobName, jobClassName, jobGroupName, cronExpression);
    }

    private void addJob(String jobName, String jobClassName, String jobGroupName, String cronExpression) throws Exception {

        // 通过SchedulerFactory获取一个调度器实例
//		SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = schedulerFactoryBean.getScheduler();
        // 启动调度器
        sched.start();
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobName, jobGroupName).build();
        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();
        try {
            sched.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }


    @PostMapping(value = "/pausejob")
    public void pausejob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        jobPause(jobClassName, jobGroupName);
    }

    private void jobPause(String jobClassName, String jobGroupName) throws Exception {
//        通过SchedulerFactory获取一个调度器实例
//        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = schedulerFactoryBean.getScheduler();
        sched.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @PostMapping(value = "/resumejob")
    public void resumejob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        jobresume(jobClassName, jobGroupName);
    }

    private void jobresume(String jobClassName, String jobGroupName) throws Exception {
//        通过SchedulerFactory获取一个调度器实例
//        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = schedulerFactoryBean.getScheduler();
        sched.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @PostMapping(value = "/reschedulejob")
    public void rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName,
                              @RequestParam(value = "jobGroupName") String jobGroupName,
                              @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        jobreschedule(jobClassName, jobGroupName, cronExpression);
    }

    private void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
//            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败" + e);
            throw new Exception("更新定时任务失败");
        }
    }


    @PostMapping(value = "/deletejob")
    public void deletejob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        jobdelete(jobClassName, jobGroupName);
    }

    private void jobdelete(String jobClassName, String jobGroupName) throws Exception {
        // 通过SchedulerFactory获取一个调度器实例
//        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = schedulerFactoryBean.getScheduler();
        sched.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        sched.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        sched.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @GetMapping(value = "/queryjob")
    public Map<String, Object> queryjob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<JobAndTrigger> jobAndTrigger = jobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        return map;
    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }


}

