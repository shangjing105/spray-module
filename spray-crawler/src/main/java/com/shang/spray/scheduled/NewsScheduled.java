package com.shang.spray.scheduled;

import com.shang.spray.entity.Config;
import com.shang.spray.pipeline.NewsPipeline;
import com.shang.spray.processor.DevelopersProcessor;
import com.shang.spray.processor.JianShuProcessor;
import com.shang.spray.processor.ZhiHuProcessor;
import com.shang.spray.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * info:新闻定时任务
 * Created by shang on 16/8/22.
 */
@Component
public class NewsScheduled {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private NewsPipeline newsPipeline;
    @Autowired
    private ConfigService configService;

    /**
     * 开发者头条
     */
    ////[秒] [分] [小时] [日] [月] [周] [年]
    @Scheduled(cron = "0 0 0/3 * * ? ")//从0点开始,每3个小时执行一次
    public void developersScheduled() {
        Specification<Config> specification = new Specification<Config>() {
            @Override
            public Predicate toPredicate(Root<Config> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("code"), "DevelopersScheduled"));
                return null;
            }
        };
        Config config = configService.findOne(specification);
        if (config != null && config.getContent().equals("1")) {
            System.out.println("----开始执行开发者头条定时任务");
            Spider spider = Spider.create(new DevelopersProcessor());
            spider.addUrl("http://toutiao.io/prev/" + sdf.format(new Date()));
            spider.addPipeline(newsPipeline);
            spider.thread(5);
            spider.setExitWhenComplete(true);
            spider.start();
            spider.stop();
        }
    }

    /**
     * 简书
     */
    @Scheduled(cron = "0 0 0/2 * * ? ")//从0点开始,每2个小时执行一次
    public void jianShuScheduled() {
        Specification<Config> specification = new Specification<Config>() {
            @Override
            public Predicate toPredicate(Root<Config> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("code"), "JianShuScheduled"));
                return null;
            }
        };
        Config config = configService.findOne(specification);
        if (config != null && config.getContent().equals("1")) {
            System.out.println("----开始执行简书定时任务");
            Spider spider = Spider.create(new JianShuProcessor());
            spider.addUrl("http://www.jianshu.com");
            spider.addPipeline(newsPipeline);
            spider.thread(5);
            spider.setExitWhenComplete(true);
            spider.start();
            spider.stop();
        }
    }

    /**
     * 知乎发现
     */
    @Scheduled(cron = "0 0 0/4 * * ? ")//从0点开始,每4个小时执行一次
    public void zhiHuScheduled() {
        Specification<Config> specification = new Specification<Config>() {
            @Override
            public Predicate toPredicate(Root<Config> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("code"), "ZhiHuScheduled"));
                return null;
            }
        };
        Config config = configService.findOne(specification);
        if (config != null && config.getContent().equals("1")) {
            System.out.println("----开始执行知乎发现定时任务");
            Spider spider = Spider.create(new ZhiHuProcessor());
            spider.addUrl("https://www.zhihu.com/explore");
            spider.addPipeline(newsPipeline);
            spider.thread(5);
            spider.setExitWhenComplete(true);
            spider.start();
            spider.stop();
        }
    }

}
