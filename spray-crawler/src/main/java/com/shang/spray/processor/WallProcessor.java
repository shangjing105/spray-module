package com.shang.spray.processor;

import com.shang.spray.utils.Constant;
import com.shang.spray.entity.Beautiful;
import com.shang.spray.entity.Sources;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * info:wall美图爬虫
 * Created by shang on 16/8/30.
 */
@Component
public class WallProcessor implements PageProcessor {
    private Site site = Site.me()
            .setDomain("3gbizhi.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
    ;

    public static final String list = "http://www.3gbizhi.com/lists-%E5%85%A8%E9%83%A8/\\w+.html";

    public static final String item = "http://www.3gbizhi.com/\\w+/\\w+.html";


    @Override
    public void process(Page page) {
        if (page.getUrl().regex(list).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class='leftc']/ul/li").links().regex(item).all());
        } else {
            Beautiful beautiful=new Beautiful();
            beautiful.setLink(page.getHtml().xpath("//div[@class='imgtitlec']").links().toString());
            beautiful.setSources(new Sources(Constant.Sources_3GBiZhi));
            if (StringUtils.isNotEmpty(beautiful.getLink())) {
                page.putField("beautiful", beautiful);
            }
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider=Spider.create(new WallProcessor());
        spider.addUrl("http://www.3gbizhi.com/lists-%E5%85%A8%E9%83%A8/1.html");
//        spider.addPipeline(new WallProcessor);
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
    }
}
