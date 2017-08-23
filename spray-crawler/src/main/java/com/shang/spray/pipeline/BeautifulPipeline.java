package com.shang.spray.pipeline;


import com.shang.spray.entity.Beautiful;
import com.shang.spray.repository.BeautifulRepository;
import com.shang.spray.utils.specification.Criteria;
import com.shang.spray.utils.specification.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.Map;

/**
 * info:美图爬虫
 * Created by shang on 16/8/30.
 */
@Repository
public class BeautifulPipeline implements Pipeline{

    @Autowired
    private BeautifulRepository beautifulRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if (entry.getKey().equals("beautiful")) {
                Beautiful beautiful=(Beautiful) entry.getValue();
                Criteria<Beautiful> criteria = new Criteria<>();
                criteria.add(Restrictions.eq("link", beautiful.getLink()));
                if (beautifulRepository.findOne(criteria) == null) {//检查链接是否已存在
                    beautiful.setTypeId(1);
                    beautiful.setAuthor("水花");
                    beautiful.setCreateDate(new Date());
                    beautiful.setStatus(1);
                    beautiful.setPlacedTop(false);
                    beautiful.setRecommend(false);
                    beautiful.setModifyDate(new Date());
                    beautifulRepository.save(beautiful);
                }
            }

        }
    }
}
