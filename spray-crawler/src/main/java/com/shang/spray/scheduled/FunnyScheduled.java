package com.shang.spray.scheduled;

import com.shang.spray.processor.DuanZiGeProcessor;
import com.shang.spray.entity.Config;
import com.shang.spray.pipeline.FunnyPipeline;
import com.shang.spray.service.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * info:笑话定时任务
 * Created by shang on 16/8/30.
 */
@Component
public class FunnyScheduled {

    @Autowired
    private FunnyPipeline funnyPipeline;
    @Autowired
    private ConfigService configService;

    /**
     * 段子哥
     */
    ////[秒] [分] [小时] [日] [月] [周] [年]
    @Scheduled(cron = "0 0 0/4 * * ? ")//从0点开始,每4个小时执行一次
    public void duanZiGeScheduled() {
        Specification<Config> specification=new Specification<Config>() {
            @Override
            public Predicate toPredicate(Root<Config> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("code"),"DuanZiGeScheduled"));
                return null;
            }
        };
        Config config=configService.findOne(specification);
        if (config != null && config.getContent().equals("1")) {
            Specification<Config> specification1=new Specification<Config>() {
                @Override
                public Predicate toPredicate(Root<Config> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    criteriaQuery.where(criteriaBuilder.equal(root.get("code"),"DuanZiGeScheduledNum"));
                    return null;
                }
            };
            Config config1=configService.findOne(specification1);
            if (config1 != null && StringUtils.isNotEmpty(config.getContent())) {
                System.out.println("----开始段子哥定时任务");
                Spider spider=Spider.create(new DuanZiGeProcessor());
                int num=Integer.valueOf(config1.getContent())+1;
                System.out.println(config1.getContent());
                spider.addUrl("http://www.duanzige.cn/list-2-"+num+".html");
                spider.addPipeline(funnyPipeline);
                spider.thread(5);
                spider.setExitWhenComplete(true);
                spider.start();
                spider.stop();
                config1.setContent(num+"");
                configService.save(config1);
            }
        }

    }
}
