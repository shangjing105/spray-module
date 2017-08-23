package com.shang.spray.job;

import com.shang.spray.processor.SsyerRest;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

/**
 * info:别样图片网
 * Created by shang on 2017/8/22.
 */
public class SsyerJob implements BaseJob {

    @Resource
    private SsyerRest ssyerRest;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ssyerRest.getRestSsyer();
    }
}
