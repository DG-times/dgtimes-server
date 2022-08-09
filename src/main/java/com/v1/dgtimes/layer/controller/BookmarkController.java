package com.v1.dgtimes.layer.controller;

/*
설명 : BookmarkController 구현햇습니다.

작성일 : 2022.08.08

마지막 수정한 사람 : 공상욱

*/

import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.dto.request.BookmarkRequestDto;
import com.v1.dgtimes.layer.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequiredArgsConstructor
public class BookmarkController {


    private final BookmarkService bookmarkService;

    //즐겨찾기
    @PostMapping("/api/bookmarks")
    public ResponseEntity<BookmarkRequestDto> bookmarks (@RequestParam Keyword keyword) {
        return new ResponseEntity(bookmarkService.pstBookmarkKeyword(new BookmarkRequestDto(keyword)), HttpStatus.OK);
    }



}
