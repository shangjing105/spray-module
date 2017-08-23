package com.shang.spray.controller;

import com.shang.spray.base.BaseApiResult;
import com.shang.spray.entity.Funny;
import com.shang.spray.utils.ModelHelper;
import com.shang.spray.utils.specification.Criteria;
import com.shang.spray.utils.specification.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * info:搞笑api
 * Created by hyr on 16/8/13.
 */
@RestController
@RequestMapping("/api/funny")
public class FunnyApiController extends BaseController{


    @RequestMapping("")
    public Map<String,Object> index(BaseApiResult result, @RequestParam(defaultValue = "0")Integer page, @RequestParam(defaultValue = "15") Integer size) {
        Map<String,Object> map=createMap();
        try {
            Sort sort=new Sort(Sort.Direction.DESC,"placedTop","recommend","createDate").and(new Sort(Sort.Direction.ASC,"sort"));
            Criteria<Funny> criteria = new Criteria<Funny>();
            criteria.add(Restrictions.eq("status", Funny.StatusEnum.SHANGJIA.getCode()));
            Pageable pageable=new PageRequest(page,size,sort);
            Page<Funny> funnies= funnyService.findAllApi(criteria,pageable);
            map.put("funny",funnies.getContent());
            map.put("last",funnies.isLast());
            map.put("result", ModelHelper.OK(result));
        } catch (Exception e) {
            _logger.error(getTrace(e));
            map.put("result",ModelHelper.ERROR(result));
        }
        return map;
    }
}
