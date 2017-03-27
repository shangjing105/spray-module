package com.shang.spray.pipeline;

import com.shang.spray.entity.Funny;
import com.shang.spray.repository.FunnyRepository;
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
 * info:笑话
 * Created by shang on 16/8/30.
 */
@Repository
public class FunnyPipeline implements Pipeline {
    @Autowired
    private FunnyRepository funnyRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().equals("funny")) {
                Funny funny=(Funny) entry.getValue();
                Specification<Funny> specification=new Specification<Funny>() {
                    @Override
                    public Predicate toPredicate(Root<Funny> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                        return criteriaBuilder.and(criteriaBuilder.equal(root.get("title"),funny.getTitle()));
                    }
                };
                if (funnyRepository.findOne(specification) == null) {//检查标题是否已存在
                    funny.setTypeId(1);
                    funny.setAuthor("水花");
                    funny.setCreateDate(new Date());
                    funny.setStatus(1);
                    funny.setModifyDate(new Date());
                    funnyRepository.save(funny);
                }
            }

        }
    }
}
