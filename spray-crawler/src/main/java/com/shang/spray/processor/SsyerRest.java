package com.shang.spray.processor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shang.spray.entity.Beautiful;
import com.shang.spray.entity.News;
import com.shang.spray.entity.Sources;
import com.shang.spray.repository.BeautifulRepository;
import com.shang.spray.repository.NewsRepository;
import com.shang.spray.utils.Constant;
import com.shang.spray.utils.specification.Criteria;
import com.shang.spray.utils.specification.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * info:别样图片网
 * Created by shang on 2017/8/22.
 */
@Service
public class SsyerRest {

    @Resource
    public RestTemplate restTemplate;

    @Resource
    private BeautifulRepository beautifulRepository;

    public void getRestSsyer() {
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("start", String.valueOf(new Random().nextInt(10000)));
        requestEntity.add("limit", String.valueOf(20));
        JSONObject data = restTemplate.postForObject("http://www.ssyer.com/pc/order/approveOrderList", requestEntity, JSONObject.class);
        JSONArray jsonArray = data.getJSONObject("data").getJSONArray("datas");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Criteria<Beautiful> criteria = new Criteria<>();
            criteria.add(Restrictions.eq("link", jsonObject.getString("pictureUrl")));
            if (beautifulRepository.findOne(criteria) == null) {//检查链接是否已存在
                Beautiful beautiful=new Beautiful();
                beautiful.setLink(jsonObject.getString("pictureUrl"));
                beautiful.setSources(new Sources(Constant.Sources_3GBiZhi));
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
