package com.v1.dgtimes.layer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
/*
설명 : 페이지 렌더링 컨트롤로 클래스를 구현햇습니다.

작성일 : 2022.08.07

마지막 수정한 사람 : 김선진

Todo -
*/
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
        return new ModelAndView("signin-error");
    }

    @GetMapping("/signup")
    public ModelAndView signup(){
        return new ModelAndView("signup");
    }
}
