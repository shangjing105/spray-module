package com.shang.spray.pipeline;

import com.shang.spray.entity.News;
import com.shang.spray.repository.NewsRepository;
import com.shang.spray.utils.specification.Criteria;
import com.shang.spray.utils.specification.Restrictions;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * info:新闻
 * Created by shang on 16/8/22.
 */
@Service
public class NewsPipeline implements Pipeline {

    @Resource
    protected NewsRepository newsRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().contains("news")) {
                News news=(News) entry.getValue();
                Criteria<News> criteria = new Criteria<>();
                criteria.add(Restrictions.eq("link", news.getLink()));
                if (newsRepository.findOne(criteria) == null) {//检查链接是否已存在
                    news.setAuthor("水花");
                    news.setSort(1);
                    news.setStatus(1);
                    news.setPlacedTop(false);
                    news.setRecommend(false);
                    news.setThumbUp(0);
                    news.setExplicitLink(true);
                    news.setCreateDate(new Date());
                    news.setModifyDate(new Date());
                    newsRepository.save(news);
                }
            }

        }
    }
}
