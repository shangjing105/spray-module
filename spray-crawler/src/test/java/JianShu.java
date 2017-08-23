import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shang.spray.SprayCrawlerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * info:简书首页爬虫
 * Created by shang on 16/9/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SprayCrawlerApplication.class)
public class JianShu {

    @Resource
    public RestTemplate restTemplate;


    @Test
    public void getRest() {
        JSONArray jsonArray = restTemplate.getForObject("http://www.jianshu.com/mobile/trending/now?page=1&count=15", JSONArray.class);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("object").getJSONObject("data");
            jsonObject.getString("title");
        }
    }

}
