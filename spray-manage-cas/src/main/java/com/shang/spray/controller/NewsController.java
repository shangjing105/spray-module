package com.shang.spray.controller;

import com.shang.spray.entity.News;
import com.shang.spray.entity.Sources;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * info:
 * Created by shang on 16/7/27.
 */
@RestController
@RequestMapping("/news")
public class NewsController extends BaseController {

    @RequestMapping("")
    public ModelAndView index(@RequestParam(defaultValue = "0")Integer page, @RequestParam(defaultValue = "10") Integer size,
                              @RequestParam(defaultValue = "1") Integer status,String search,ModelAndView view) {
        try {
            Specification<News> specification=new Specification<News>() {
                @Override
                public Predicate toPredicate(Root<News> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> list = new ArrayList<>();
                    if (status!=null&&status!=-1) {
                        list.add(criteriaBuilder.equal(root.get("status"),status));
                    }
                    if (StringUtils.isNotEmpty(search)) {
                        list.add(criteriaBuilder.like(root.get("title"),"%"+search+"%"));
                    }
                    Predicate[] p = new Predicate[list.size()];
                    return criteriaBuilder.and(list.toArray(p));
                }
            };
            Sort sort=new Sort(Sort.Direction.DESC,"placedTop","recommend","createDate").and(new Sort(Sort.Direction.ASC,"sort"));
            Pageable pageable=new PageRequest(page,size,sort);
            view.addObject("news",newsService.findAll(specification,pageable));
            view.addObject("status",status);
            view.addObject("search",search);
        } catch (Exception e) {
            _logger.error(getTrace(e));
        }
        view.setViewName("news/list");
        return view;
    }


    @RequestMapping(value = "/addLink",method = RequestMethod.GET)
    public ModelAndView addLink(ModelAndView view,Integer id) {
        Specification<Sources> specification=new Specification<Sources>() {
            @Override
            public Predicate toPredicate(Root<Sources> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("status"),1));
                return criteriaQuery.getRestriction();
            }
        };
        view.addObject("sources",sourcesService.findAll(specification));
        if (id != null) {
            News news=newsService.findOne(id);
            if (news != null && news.getExplicitLink()) {
                view.setViewName("news/add_link");
            } else {
                view.setViewName("news/add_news");
            }
            view.addObject("news",news);
        }
        return view;
    }

    @RequestMapping(value = "/addLink",method = RequestMethod.POST)
    public ModelAndView addLink(News news,ModelAndView view) {
        news.setSources(sourcesService.findOne(news.getSources().getId()));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        news.setAuthor(user.getUsername());
        news.setInfo(news.getTitle());
        news.setTypeId(1);
        news.setStatus(1);
        news.setCreateDate(new Date());
        news.setModifyDate(new Date());
        newsService.save(news);
        view.setViewName("redirect:/news");
        return view;
    }

    @RequestMapping(value = "/addNews",method = RequestMethod.GET)
    public ModelAndView addNews(ModelAndView view) {
        Specification<Sources> specification=new Specification<Sources>() {
            @Override
            public Predicate toPredicate(Root<Sources> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("status"),1));
                return criteriaQuery.getRestriction();
            }
        };
        view.addObject("sources",sourcesService.findAll(specification));
        view.setViewName("news/add_news");
        return view;
    }

    @RequestMapping(value = "/closeNews/{id}",method = RequestMethod.GET)
    public ModelAndView closeNews(@PathVariable Integer id, ModelAndView view) {
        News news=newsService.findOne(id);
        if (news.getStatus().equals(News.StatusEnum.SHANGJIA.getCode())) {
            news.setStatus(News.StatusEnum.XIAJIA.getCode());
        } else {
            news.setStatus(News.StatusEnum.SHANGJIA.getCode());
        }
        newsService.save(news);
        view.setViewName("redirect:/news");
        return view;
    }

    @RequestMapping(value = "/delNews/{id}",method = RequestMethod.GET)
    public ModelAndView delNews(@PathVariable Integer id, ModelAndView view) {
        newsService.delete(id);
        view.setViewName("redirect:/news");
        return view;
    }



}
