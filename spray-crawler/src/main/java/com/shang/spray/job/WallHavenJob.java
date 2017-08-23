package com.shang.spray.job;

import com.shang.spray.pipeline.BeautifulPipeline;
import com.shang.spray.processor.WallHeavnProcessor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.util.Random;

/**
 * info:
 * Created by shang on 2017/8/23.
 */
public class WallHavenJob implements BaseJob {

    @Resource
    private BeautifulPipeline beautifulPipeline;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Spider spider=Spider.create(new WallHeavnProcessor());
        spider.addUrl("https://alpha.wallhaven.cc/latest?page="+ new Random().nextInt(10000));
        spider.addPipeline(beautifulPipeline);
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
    }
}
