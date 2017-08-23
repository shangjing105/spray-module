package com.shang.spray.job;

import com.shang.spray.pipeline.NewsPipeline;
import com.shang.spray.processor.DevelopersProcessor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * info:开发者头条任务
 * Created by shang on 2017/8/21.
 */
public class KaiFaZheTouTiaoJob implements BaseJob {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private NewsPipeline newsPipeline;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.error("===========开始执行开发者头条定时任务");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Spider spider = Spider.create(new DevelopersProcessor());
        spider.addUrl("http://toutiao.io/prev/" + sdf.format(new Date()));
        spider.addPipeline(newsPipeline);
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
    }
}
