package com.shang.spray.controller;

import com.shang.spray.base.BaseApiResult;
import com.shang.spray.entity.Beautiful;
import com.shang.spray.utils.ModelHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * info:美图controller
 * Created by shang on 16/7/21.
 */
@RestController
@RequestMapping("/api/beautiful")
public class BeautifulApiController extends BaseController {

    @RequestMapping("")
    public Map<String,Object> index(BaseApiResult result, @RequestParam(defaultValue = "0")Integer page, @RequestParam(defaultValue = "12") Integer size) {
        Map<String,Object> map=createMap();
        try {
            Sort sort=new Sort(Sort.Direction.DESC,"placedTop","recommend","createDate");
            Specification<Beautiful> specification=new Specification<Beautiful>() {
                @Override
                public Predicate toPredicate(Root<Beautiful> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    criteriaQuery.where(criteriaBuilder.equal(root.get("status"),Beautiful.StatusEnum.SHANGJIA.getCode()));
                    return null;
                }
            };
            Pageable pageable=new PageRequest(page,size,sort);
            Page<Beautiful> beautiful=beautifulService.findAllApi(specification,pageable);
            List<Map<String,List<Beautiful>>> beautifulList=new ArrayList<>();
            Map<String,List<Beautiful>> stulist=new HashMap<>();
            List<Beautiful> list=new ArrayList<>();
            boolean st=false,stat=false;
            for (int i = 0; i < beautiful.getContent().size(); i++) {
                if (i%6==0) {
                    list=new ArrayList<>();
                    stulist=new HashMap<>();
                    stulist.put("items3",list);
                    beautifulList.add(stulist);
                    st=true;stat=true;
                }
                list.add(beautiful.getContent().get(i));
                if (list.size() == 3&st) {
                    list=new ArrayList<>();
                    stulist=new HashMap<>();
                    stulist.put("items2",list);
                    beautifulList.add(stulist);
                    st=false;
                }
                if (list.size() == 2 && stat && !st) {
                    list=new ArrayList<>();
                    stulist.put("items1",list);
                    stat=false;
                }

            }
            map.put("beautifulList",beautifulList);
            map.put("last",beautiful.isLast());
            map.put("result", ModelHelper.OK(result));
        } catch (Exception e) {
            _logger.error(getTrace(e));
            map.put("result",ModelHelper.ERROR(result));
        }
        return map;
    }
}
