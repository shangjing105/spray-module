package com.shang.spray.processor;

import com.shang.spray.utils.Constant;
import com.shang.spray.entity.News;
import com.shang.spray.entity.Sources;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * info:知乎发现
 * Created by shang on 16/9/9.
 */
public class ZhiHuProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("zhihu.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");

    public static final String list = "https://www.zhihu.com/explore";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(list).match()) {
            List<Selectable> list=page.getHtml().xpath("//div[@data-type='daily']/div[@class='explore-feed feed-item']").nodes();
            for (Selectable s : list) {
                String title=s.xpath("//h2/a/text()").toString();
                String link=s.xpath("//h2").links().toString();
                if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(link)) {
                    News news = new News();
                    news.setTitle(title);
                    news.setInfo(title);
                    news.setLink(link);
                    news.setTypeId(Constant.Type_ZhiHu);
                    news.setSources(new Sources(Constant.Sources_ZhiHu));
                    page.putField("news" + title, news);
                }
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider=Spider.create(new ZhiHuProcessor());
        spider.addUrl("https://www.zhihu.com/explore");
//        spider.addPipeline(new NewsPipeline());
        spider.thread(5);
        spider.setExitWhenComplete(true);
        spider.start();
    }
}
