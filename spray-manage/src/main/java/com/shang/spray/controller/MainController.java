package com.shang.spray.controller;

import com.shang.spray.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * info:
 * Created by shang on 16/7/26.
 */
@RestController
@RequestMapping("/main")
public class MainController extends BaseController{

    @RequestMapping(value = "")
    public ModelAndView index(ModelAndView view) {
        view.setViewName("index");
        return view;
    }
}
