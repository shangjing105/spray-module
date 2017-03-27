package com.shang.spray.controller;

import com.shang.spray.controller.BaseController;
import com.shang.spray.entity.Config;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * info:
 * Created by shang on 16/8/30.
 */
@RestController
@RequestMapping("/config")
public class ConfigController extends BaseController {


    @RequestMapping("")
    public ModelAndView index(@RequestParam(defaultValue = "0")Integer page, @RequestParam(defaultValue = "10") Integer size, ModelAndView view) {
        try {
            Sort sort=new Sort(Sort.Direction.DESC,"createDate","id");
            Pageable pageable=new PageRequest(page,size,sort);
            view.addObject("configs",configService.findAll(pageable));
        } catch (Exception e) {
            _logger.error(getTrace(e));
        }
        view.setViewName("config/list");
        return view;
    }

    @RequestMapping(value = "/addLink",method = RequestMethod.GET)
    public ModelAndView addLink(ModelAndView view,Integer id) {
        if (id != null) {
            view.addObject("config",configService.findOne(id));
        }
        view.setViewName("config/add_link");
        return view;
    }

    @RequestMapping(value = "/addLink",method = RequestMethod.POST)
    public ModelAndView addLink(Config config, ModelAndView view) {
        config.setStatus(Config.StatusEnum.SHANGJIA.getCode());
        config.setCreateDate(new Date());
        config.setModifyDate(new Date());
        configService.save(config);
        view.setViewName("redirect:/config");
        return view;
    }


    @RequestMapping(value = "/closeSources/{id}",method = RequestMethod.GET)
    public ModelAndView closeNews(@PathVariable Integer id, ModelAndView view) {
        Config config=configService.findOne(id);
        if (config.getStatus().equals(Config.StatusEnum.SHANGJIA.getCode())) {
            config.setStatus(Config.StatusEnum.XIAJIA.getCode());
        } else {
            config.setStatus(Config.StatusEnum.SHANGJIA.getCode());
        }
        configService.save(config);
        view.setViewName("redirect:/config");
        return view;
    }

    @RequestMapping(value = "/del/{id}",method = RequestMethod.GET)
    public ModelAndView delFunny(@PathVariable Integer id, ModelAndView view) {
        configService.delete(id);
        view.setViewName("redirect:/config");
        return view;
    }

}
