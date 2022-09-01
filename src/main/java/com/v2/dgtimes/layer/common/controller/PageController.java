package com.v2.dgtimes.layer.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    @GetMapping("/")
    public ModelAndView main(){
        return new ModelAndView("index");
    }

    @GetMapping("/signin")
    public ModelAndView signin(){
        return new ModelAndView("signin");
    }

    @GetMapping("/login-error")
    public ModelAndView signInError(){
        return new ModelAndView("login-error");
    }

    @GetMapping("/signup")
    public ModelAndView signup(){
        return new ModelAndView("signup");
    }
}
