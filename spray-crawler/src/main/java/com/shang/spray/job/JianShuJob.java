package com.shang.spray.job;

import com.shang.spray.processor.JianShuRest;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 简书任务
 */
public class JianShuJob implements BaseJob {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JianShuRest jianShuRest;

    public void execute(JobExecutionContext context)
        throws JobExecutionException {
        logger.error("===========开始执行简书定时任务");
        jianShuRest.getRestJianShu();
    }
}  