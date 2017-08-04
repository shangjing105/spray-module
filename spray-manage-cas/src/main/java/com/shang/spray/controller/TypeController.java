package com.shang.spray.controller;

import com.shang.spray.controller.BaseController;
import com.shang.spray.entity.News;
import com.shang.spray.entity.Type;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * info:
 * Created by hyr on 16/8/12.
 */
@RestController
@RequestMapping("/type")
public class TypeController extends BaseController{

    @RequestMapping("")
    public ModelAndView index(@RequestParam(defaultValue = "0")Integer page, @RequestParam(defaultValue = "10") Integer size, ModelAndView view) {
        try {
            Sort sort=new Sort(Sort.Direction.DESC,"createDate","id");
            Pageable pageable=new PageRequest(page,size,sort);
            view.addObject("type",typeService.findAll(pageable));
        } catch (Exception e) {
            _logger.error(getTrace(e));
        }
        view.setViewName("type/list");
        return view;
    }

    @RequestMapping(value = "/addLink",method = RequestMethod.GET)
    public ModelAndView addLink(ModelAndView view,Integer id) {
        if (id != null) {
            view.addObject("type",typeService.findOne(id));
        }
        view.setViewName("type/add_link");
        return view;
    }

    @RequestMapping(value = "/addLink",method = RequestMethod.POST)
    public ModelAndView addLink(Type type, ModelAndView view) {
        type.setStatus(Type.StatusEnum.SHANGJIA.getCode());
        type.setCreateDate(new Date());
        type.setModifyDate(new Date());
        typeService.save(type);
        view.setViewName("redirect:/type");
        return view;
    }

    @RequestMapping(value = "/closeType/{id}",method = RequestMethod.GET)
    public ModelAndView closeNews(@PathVariable Integer id, ModelAndView view) {
        Type type=typeService.findOne(id);
        if (type.getStatus().equals(Type.StatusEnum.SHANGJIA.getCode())) {
            type.setStatus(Type.StatusEnum.XIAJIA.getCode());
        } else {
            type.setStatus(Type.StatusEnum.SHANGJIA.getCode());
        }
        typeService.save(type);
        view.setViewName("redirect:/type");
        return view;
    }

    @RequestMapping(value = "/delType/{id}",method = RequestMethod.GET)
    public ModelAndView delNews(@PathVariable Integer id, ModelAndView view) {
        Specification<News> specification=new Specification<News>() {
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("typeId"),typeService.findOne(id).getId()));
                return null;
            }
        };
        List<News> newsList=newsService.findAll(specification);
        if (newsList.size() == 0) {
            typeService.delete(id);
        }
        view.setViewName("redirect:/type");
        return view;
    }
}
