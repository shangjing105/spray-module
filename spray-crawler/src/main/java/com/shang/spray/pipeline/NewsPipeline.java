package com.shang.spray.pipeline;

import com.shang.spray.entity.News;
import com.shang.spray.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.Map;

/**
 * info:新闻
 * Created by shang on 16/8/22.
 */
@Repository
public class NewsPipeline implements Pipeline {

    @Autowired
    protected NewsRepository newsRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().contains("news")) {
                News news=(News) entry.getValue();
                Specification<News> specification=new Specification<News>() {
                    @Override
                    public Predicate toPredicate(Root<News> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                        return criteriaBuilder.and(criteriaBuilder.equal(root.get("link"),news.getLink()));
                    }
                };
                if (newsRepository.findOne(specification) == null) {//检查链接是否已存在
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
