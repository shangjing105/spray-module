package com.shang.spray.controller;

import com.shang.spray.base.BaseApiResult;
import com.shang.spray.entity.News;
import com.shang.spray.entity.Type;
import com.shang.spray.utils.ModelHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
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
        Specification<News> specification=new Specification<News>() {
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                list.add(criteriaBuilder.equal(root.get("status"),News.StatusEnum.SHANGJIA.getCode()));
                if (!StringUtils.isEmpty(typeId)) {
                    list.add(criteriaBuilder.equal(root.get("typeId"),typeId));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        };
        Pageable pageable=new PageRequest(page,size,sort);
        return newsService.findAllApi(specification,pageable);
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
            Specification<Type> specification=new Specification<Type>() {
                @Override
                public Predicate toPredicate(Root<Type> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    criteriaQuery.where(criteriaBuilder.equal(root.get("status"),Type.StatusEnum.SHANGJIA.getCode()));
                    return null;
                }
            };
            Pageable pageable=new PageRequest(page,size,sort);
            Page<Type> news= typeService.findAllApi(specification,pageable);
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
