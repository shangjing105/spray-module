package com.shang.spray.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * info:
 * Created by shang on 16/7/26.
 */
@Controller
public class LoginController extends BaseController{

    @RequestMapping(value = "")
    public String index() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(ModelAndView view) {
        view.setViewName("login");
        return view;
    }

}
