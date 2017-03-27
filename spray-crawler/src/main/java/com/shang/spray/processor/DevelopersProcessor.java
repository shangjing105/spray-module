package com.shang.spray.processor;

import com.shang.spray.utils.Constant;
import com.shang.spray.entity.News;
import com.shang.spray.entity.Sources;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * info:开发者头条爬虫程序
 * Created by shang on 16/8/19.
 */
@Component
public class DevelopersProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("toutiao.io")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    ;

    public static final String list = "http://toutiao.io/prev/[0-9]{4}-[0-9]{2}-[0-9]{2}";

    public static final String item = "http://toutiao.io/posts/\\w+";

    public static final String url = "http://toutiao.io/j/\\w+";


    @Override
    public void process(Page page) {
        if (page.getUrl().regex(list).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class='post']").regex(item).all());
//            page.addTargetRequests(page.getHtml().links().regex(list).all());
        } else {
            String title=page.getHtml().xpath("//div[@class='content']/h3/a/text()").toString();
            String link=page.getHtml().regex(url).toString();
            if (StringUtils.isNotEmpty(title) && StringUtils.isNotEmpty(link)) {
                News news = new News();
                news.setTitle(title);
                news.setInfo(title);
                news.setLink(link);
                news.setTypeId(Constant.Type_KaiFaZhe);
                news.setSources(new Sources(Constant.Sources_KaiFaZhe));
                page.putField("news", news);
            }
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

}
