package com.shang.spray.controller;

import com.shang.spray.base.BaseApiResult;
import com.shang.spray.entity.Beautiful;
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
            Criteria<Beautiful> criteria = new Criteria<Beautiful>();
            criteria.add(Restrictions.eq("status", Beautiful.StatusEnum.SHANGJIA.getCode()));
            Pageable pageable=new PageRequest(page,size,sort);
            Page<Beautiful> beautiful=beautifulService.findAllApi(criteria,pageable);
            List<Map<String,List<Beautiful>>> beautifulList=new ArrayList<Map<String,List<Beautiful>>>();
            Map<String,List<Beautiful>> stulist=new HashMap<String,List<Beautiful>>();
            List<Beautiful> list=new ArrayList<Beautiful>();
            boolean st=false,stat=false;
            for (int i = 0; i < beautiful.getContent().size(); i++) {
                if (i%6==0) {
                    list=new ArrayList<Beautiful>();
                    stulist=new HashMap<String,List<Beautiful>>();
                    stulist.put("items3",list);
                    beautifulList.add(stulist);
                    st=true;stat=true;
                }
                list.add(beautiful.getContent().get(i));
                if (list.size() == 3&st) {
                    list=new ArrayList<Beautiful>();
                    stulist=new HashMap<String,List<Beautiful>>();
                    stulist.put("items2",list);
                    beautifulList.add(stulist);
                    st=false;
                }
                if (list.size() == 2 && stat && !st) {
                    list=new ArrayList<Beautiful>();
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
