package com.shang.spray.job;

import com.shang.spray.pipeline.NewsPipeline;
import com.shang.spray.processor.ZhiHuProcessor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

/**
 * info:知乎任务
 * Created by shang on 2017/8/21.
 */
public class ZhiHuJob implements BaseJob {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private NewsPipeline newsPipeline;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.error("===========开始执行知乎定时任务");
        Spider spider = Spider.create(new ZhiHuProcessor());
        spider.addUrl("https://www.zhihu.com/explore");
        spider.addPipeline(newsPipeline);
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();

    }
}
