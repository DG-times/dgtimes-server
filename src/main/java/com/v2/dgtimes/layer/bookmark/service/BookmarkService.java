package com.v2.dgtimes.layer.bookmark.service;

import com.v2.dgtimes.config.exception.CustomException;
import com.v2.dgtimes.config.exception.ErrorCode;
import com.v2.dgtimes.config.security.userdetail.UserDetailImpl;
import com.v2.dgtimes.layer.bookmark.model.Bookmark;
import com.v2.dgtimes.layer.bookmark.repository.BookmarkRepository;
import com.v2.dgtimes.layer.news.model.dto.request.NewsRequestDto;
import com.v2.dgtimes.layer.user.model.User;
import com.v2.dgtimes.layer.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
설명 : BookmarkService
    -@Transactional(readOnly = true) 설정

작성일 : 2022.09.04

마지막 수정한 사람 : 안상록

*/
@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public Bookmark saveBookmark(NewsRequestDto newsRequestDto, UserDetailImpl userDetail){
        if (userDetail == null) {
            return null;
        }
        Bookmark bookmark = getBookmarkFromUser(userDetail);
        bookmark.update(newsRequestDto);
        return bookmarkRepository.save(bookmark);
    }

    @Transactional(readOnly = true)
    public Bookmark getBookmarkFromUser(UserDetailImpl userDetail){
        User user = userRepository.findById(userDetail.getUserId()).get();
        Bookmark bookmark = user.getBookmark();
        return bookmark;
    }
}
