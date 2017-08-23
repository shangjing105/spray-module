package com.shang.spray.processor;

import com.shang.spray.entity.Beautiful;
import com.shang.spray.entity.Sources;
import com.shang.spray.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * info:
 * Created by shang on 16/8/30.
 */
@Component
public class WallHeavnProcessor implements PageProcessor {
    private Site site = Site.me()
            .setDomain("alpha.wallhaven.cc")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
    ;

    public static final String list = "https://alpha.wallhaven.cc/latest?\\w+";

    public static final String item = "https://alpha.wallhaven.cc/wallpaper/\\d*";


    @Override
    public void process(Page page) {
        if (page.getUrl().regex(list).match()) {
            List<String> all = page.getHtml().xpath("//div[@id='thumbs']/section/ul/li").links().regex(item).all();
            HashSet h = new HashSet(all);
            all.clear();
            all.addAll(h);//去重
            page.addTargetRequests(all);
        } else {
            Beautiful beautiful=new Beautiful();
            beautiful.setLink("https:"+page.getHtml().xpath("//div[@class='scrollbox']/img/@src").get());
            beautiful.setSources(new Sources(Constant.Sources_WallHaven));
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
        Spider spider=Spider.create(new WallHeavnProcessor());
        spider.addUrl("https://alpha.wallhaven.cc/latest?page="+ new Random().nextInt(10000));
//        spider.addPipeline(new WallProcessor);
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
        spider.stop();
    }
}
