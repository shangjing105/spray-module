package com.shang.spray.controller;

import com.shang.spray.controller.BaseController;
import com.shang.spray.entity.Funny;
import com.shang.spray.entity.Sources;
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
 * Created by hyr on 16/8/13.
 */
@RestController
@RequestMapping("/funny")
public class FunnyController  extends BaseController{

    @RequestMapping("")
    public ModelAndView index(@RequestParam(defaultValue = "0")Integer page, @RequestParam(defaultValue = "10") Integer size,
                              @RequestParam(defaultValue = "1") Integer status,ModelAndView view) {
        try {
            Specification<Funny> specification=new Specification<Funny>() {
                @Override
                public Predicate toPredicate(Root<Funny> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> list = new ArrayList<>();
                    if (status!=null&&status!=-1) {
                        list.add(criteriaBuilder.equal(root.get("status"),status));
                    }
                    Predicate[] p = new Predicate[list.size()];
                    return criteriaBuilder.and(list.toArray(p));
                }
            };
            Sort sort=new Sort(Sort.Direction.DESC,"placedTop","recommend","createDate").and(new Sort(Sort.Direction.ASC,"sort"));
            Pageable pageable=new PageRequest(page,size,sort);
            view.addObject("funny",funnyService.findAll(specification,pageable));
            view.addObject("status",status);

        } catch (Exception e) {
            _logger.error(getTrace(e));
        }
        view.setViewName("funny/list");
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
            view.addObject("funny",funnyService.findOne(id));
        }
        view.setViewName("funny/add_link");
        return view;
    }

    @RequestMapping(value = "/addLink",method = RequestMethod.POST)
    public ModelAndView addLink(Funny funny, ModelAndView view) {
        funny.setSources(sourcesService.findOne(funny.getSources().getId()));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        funny.setAuthor(user.getUsername());
        funny.setTypeId(1);
        funny.setSort(1);
        funny.setStatus(1);
        funny.setCreateDate(new Date());
        funny.setModifyDate(new Date());
        funnyService.save(funny);
        view.setViewName("redirect:/funny/addLink");
        return view;
    }

    @RequestMapping(value = "/closeFunny/{id}",method = RequestMethod.GET)
    public ModelAndView closeFunny(@PathVariable Integer id, ModelAndView view) {
        Funny funny=funnyService.findOne(id);
        if (funny.getStatus().equals(Funny.StatusEnum.SHANGJIA.getCode())) {
            funny.setStatus(Funny.StatusEnum.XIAJIA.getCode());
        } else {
            funny.setStatus(Funny.StatusEnum.SHANGJIA.getCode());
        }
        funnyService.save(funny);
        view.setViewName("redirect:/funny");
        return view;
    }

    @RequestMapping(value = "/delFunny/{id}",method = RequestMethod.GET)
    public ModelAndView delFunny(@PathVariable Integer id, ModelAndView view) {
        funnyService.delete(id);
        view.setViewName("redirect:/funny");
        return view;
    }


}
