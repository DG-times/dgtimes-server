package com.v2.dgtimes.layer.common.controller;

import com.v2.dgtimes.config.security.userdetail.UserDetailImpl;
import com.v2.dgtimes.layer.bookmark.model.Bookmark;
import com.v2.dgtimes.layer.bookmark.service.BookmarkService;
import com.v2.dgtimes.layer.searchRanking.service.SearchRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final BookmarkService bookmarkService;
    private final SearchRankingService searchRankingService;

    @GetMapping("/")
    public ModelAndView main(@AuthenticationPrincipal UserDetailImpl userDetail){
        Bookmark bookmark = new Bookmark(null, "", "");

        if (userDetail != null) {
            bookmark = bookmarkService.getBookmarkFromUser(userDetail);
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("include_keyword", bookmark.getIncludeKeywordList());
        modelAndView.addObject("exclude_keyword", bookmark.getExcludeKeywordList());
        modelAndView.addObject("exclude_keyword", bookmark.getExcludeKeywordList());
        modelAndView.addObject("keyword_ranking_list", searchRankingService.SearchRankingVariation());

        return modelAndView;
    }

    @GetMapping("/signin")
    public ModelAndView signin(){
        return new ModelAndView("signin");
    }

    @GetMapping("/signin-error")
    public ModelAndView signInError(){
        return new ModelAndView("signin-error");
    }

    @GetMapping("/signup")
    public ModelAndView signup(){
        return new ModelAndView("signup");
    }
}
