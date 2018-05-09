package com.shang.spray.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * info:
 * Created by shang on 16/7/26.
 */
@RestController
public class MainController extends BaseController{



    @RequestMapping(value = "")
    public ModelAndView index(ModelAndView view) {
        view.setViewName("index");
        return view;
    }


    @RequestMapping(value = "/main")
    public ModelAndView main(ModelAndView view) {
        view.setViewName("index");
        return view;
    }
}
