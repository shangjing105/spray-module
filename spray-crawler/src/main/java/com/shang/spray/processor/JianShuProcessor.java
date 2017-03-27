package com.shang.spray.processor;

import com.shang.spray.utils.Constant;
import com.shang.spray.entity.News;
import com.shang.spray.entity.Sources;
import com.shang.spray.pipeline.NewsPipeline;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * info:简书首页爬虫
 * Created by shang on 16/9/9.
 */
public class JianShuProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("jianshu.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
    ;

    public static final String list = "http://www.jianshu.com";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(list).match()) {
            List<Selectable> list=page.getHtml().xpath("//ul[@class='article-list thumbnails']/li").nodes();
            for (Selectable s : list) {
                String title=s.xpath("//div/h4/a/text()").toString();
                String link=s.xpath("//div/h4").links().toString();
                if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(link)) {
                    News news=new News();
                    news.setTitle(title);
                    news.setInfo(title);
                    news.setLink(link);
                    news.setTypeId(Constant.Type_JianShu);
                    news.setSources(new Sources(Constant.Sources_JianShu));
                    page.putField("news"+title, news);
                }
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider=Spider.create(new JianShuProcessor());
        spider.addUrl("http://www.jianshu.com");
        spider.addPipeline(new NewsPipeline());
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
    }
}
