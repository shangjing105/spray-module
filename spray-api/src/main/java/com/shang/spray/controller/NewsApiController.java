package com.shang.spray.controller;

import com.shang.spray.base.BaseApiResult;
import com.shang.spray.entity.News;
import com.shang.spray.entity.Type;
import com.shang.spray.utils.ModelHelper;
import com.shang.spray.utils.specification.Criteria;
import com.shang.spray.utils.specification.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * info:精选controller
 * Created by shang on 16/7/8.
 */
@RestController
public class NewsApiController extends BaseController {



    @RequestMapping("/api/news")
    public Map<String,Object> index(BaseApiResult result,@RequestParam(defaultValue = "0")Integer page,@RequestParam(defaultValue = "15") Integer size,Integer typeId) {
        Map<String,Object> map=createMap();
        try {
            Page<News> news = getNewses(page, size, typeId);
            map.put("news",news.getContent());
            map.put("last",news.isLast());
            map.put("result", ModelHelper.OK(result));
        } catch (Exception e) {
            _logger.error(getTrace(e));
            map.put("result",ModelHelper.ERROR(result));
        }
        return map;
    }

    private Page<News> getNewses(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "15") Integer size, final Integer typeId) {
        Sort sort=new Sort(Sort.Direction.DESC,"placedTop","recommend","createDate").and(new Sort(Sort.Direction.ASC,"sort"));
        Criteria<News> criteria = new Criteria<News>();
        criteria.add(Restrictions.eq("status", News.StatusEnum.SHANGJIA.getCode()));
        criteria.add(Restrictions.eq("typeId", typeId));
        Pageable pageable=new PageRequest(page,size,sort);
        return newsService.findAllApi(criteria,pageable);
    }


    @RequestMapping("/api/news/details/{id}")
    public Map<String,Object> details(BaseApiResult result,@PathVariable Integer id) {
        Map<String,Object> map=createMap();
        try {
            map.put("news",newsService.findOneApi(id));
            map.put("result", ModelHelper.OK(result));
        } catch (Exception e) {
            _logger.error(getTrace(e));
            map.put("result",ModelHelper.ERROR(result));
        }
        return map;
    }

    @RequestMapping("/api/type")
    public Map<String,Object> type(BaseApiResult result,@RequestParam(defaultValue = "0")Integer page,@RequestParam(defaultValue = "15") Integer size) {
        Map<String,Object> map=createMap();
        try {
            Sort sort=new Sort(Sort.Direction.ASC,"sort").and(new Sort(Sort.Direction.DESC,"createDate"));
            Criteria<Type> criteria = new Criteria<Type>();
            criteria.add(Restrictions.eq("status", News.StatusEnum.SHANGJIA.getCode()));
            Pageable pageable=new PageRequest(page,size,sort);
            Page<Type> news= typeService.findAllApi(criteria,pageable);
            for (Type type : news.getContent()) {
                Page<News> newses=getNewses(page,size,type.getId());
                type.setNewsList(newses.getContent());
                type.setLast(newses.isLast());
            }
            map.put("type",news.getContent());
            map.put("result", ModelHelper.OK(result));
        } catch (Exception e) {
            _logger.error(getTrace(e));
            map.put("result",ModelHelper.ERROR(result));
        }
        return map;
    }
}
