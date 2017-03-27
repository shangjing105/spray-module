package com.shang.spray.processor;

import com.shang.spray.utils.Constant;
import com.shang.spray.entity.Funny;
import com.shang.spray.entity.Sources;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * info:段子哥
 * Created by shang on 16/8/30.
 */
public class DuanZiGeProcessor implements PageProcessor {
    private Site site = Site.me()
            .setDomain("duanzige.cn")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
    ;

    public static final String list = "http://www.duanzige.cn/list-2-\\w+.html";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(list).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class='xiaohua-data']/h3").links().all());
        } else {
            Funny funny=new Funny();
            funny.setTitle(page.getHtml().xpath("//div[@class='xiaohua-data']/h1/text()").toString());
            funny.setContent(page.getHtml().xpath("//div[@class='xiaohua-data']/div[@class='content']/p/text()").toString());
            funny.setSources(new Sources(Constant.Sources_DuanZiGe));
            if (StringUtils.isNotEmpty(funny.getTitle()) && StringUtils.isNotEmpty(funny.getContent())) {
                page.putField("funny", funny);
            }
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider=Spider.create(new DuanZiGeProcessor());
        spider.addUrl("http://www.duanzige.cn/list-2-1.html");
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
    }
}
