package com.shang.spray.controller;

import com.shang.spray.controller.BaseController;
import com.shang.spray.entity.News;
import com.shang.spray.entity.Sources;
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
@RequestMapping("/sources")
public class SourcesController extends BaseController{

    @RequestMapping("")
    public ModelAndView index(@RequestParam(defaultValue = "0")Integer page, @RequestParam(defaultValue = "10") Integer size, ModelAndView view) {
        try {
            Sort sort=new Sort(Sort.Direction.DESC,"createDate","id");
            Pageable pageable=new PageRequest(page,size,sort);
            view.addObject("sources",sourcesService.findAll(pageable));
        } catch (Exception e) {
            _logger.error(getTrace(e));
        }
        view.setViewName("sources/list");
        return view;
    }

    @RequestMapping(value = "/addLink",method = RequestMethod.GET)
    public ModelAndView addLink(ModelAndView view,Integer id) {
        if (id != null) {
            view.addObject("sources",sourcesService.findOne(id));
        }
        view.setViewName("sources/add_link");
        return view;
    }

    @RequestMapping(value = "/addLink",method = RequestMethod.POST)
    public ModelAndView addLink(Sources sources,ModelAndView view) {
        sources.setStatus(Sources.StatusEnum.SHANGJIA.getCode());
        sources.setCreateDate(new Date());
        sources.setModifyDate(new Date());
        sourcesService.save(sources);
        view.setViewName("redirect:/sources");
        return view;
    }

    @RequestMapping(value = "/closeSources/{id}",method = RequestMethod.GET)
    public ModelAndView closeNews(@PathVariable Integer id, ModelAndView view) {
        Sources sources=sourcesService.findOne(id);
        if (sources.getStatus().equals(Sources.StatusEnum.SHANGJIA.getCode())) {
            sources.setStatus(Sources.StatusEnum.XIAJIA.getCode());
        } else {
            sources.setStatus(Sources.StatusEnum.SHANGJIA.getCode());
        }
        sourcesService.save(sources);
        view.setViewName("redirect:/sources");
        return view;
    }

    @RequestMapping(value = "/delSources/{id}",method = RequestMethod.GET)
    public ModelAndView delNews(@PathVariable Integer id, ModelAndView view) {
        Specification<News> specification=new Specification<News>() {
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("sources"),sourcesService.findOne(id)));
                return null;
            }
        };
        List<News> newsList=newsService.findAll(specification);
        if (newsList.size() == 0) {
            sourcesService.delete(id);
        }
        view.setViewName("redirect:/sources");
        return view;
    }
}
