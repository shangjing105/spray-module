package com.shang.spray.processor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shang.spray.entity.News;
import com.shang.spray.entity.Sources;
import com.shang.spray.repository.NewsRepository;
import com.shang.spray.utils.Constant;
import com.shang.spray.utils.specification.Criteria;
import com.shang.spray.utils.specification.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;

/**
 * info:简书首页爬虫
 * Created by shang on 16/9/9.
 */
@Service
public class JianShuRest {
    @Resource
    public RestTemplate restTemplate;

    @Resource
    protected NewsRepository newsRepository;

    public void getRestJianShu() {
        JSONArray jsonArray = restTemplate.getForObject("http://www.jianshu.com/mobile/trending/now?page=1&count=15", JSONArray.class);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("object").getJSONObject("data");
            Criteria<News> criteria = new Criteria<>();
            criteria.add(Restrictions.eq("link", "http://www.jianshu.com/p/"+jsonObject.getString("slug")));
            if (newsRepository.findOne(criteria) == null) {//检查链接是否已存在
                News news = new News();
                news.setTitle(jsonObject.getString("title"));
                news.setInfo(jsonObject.getString("title"));
                news.setLink("http://www.jianshu.com/p/"+jsonObject.getString("slug"));
                news.setCoverUrl(jsonObject.getString("list_image_url"));
                news.setTypeId(Constant.Type_JianShu);
                news.setSources(new Sources(Constant.Sources_JianShu));
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
