package com.v1.dgtimes.layer.service;

/*
설명 : BookmarkService 수정햇습니다.

작성일 : 2022.08.12

마지막 수정한 사람 : 공상욱

*/

import com.v1.dgtimes.config.exception.CustomException;
import com.v1.dgtimes.config.exception.ErrorCode;
import com.v1.dgtimes.config.security.userdetail.UserDetailImpl;
import com.v1.dgtimes.layer.model.Bookmark;
import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.User;
import com.v1.dgtimes.layer.model.dto.request.BookmarkRequestDto;
import com.v1.dgtimes.layer.model.dto.response.DefaultResponseDto;
import com.v1.dgtimes.layer.repository.BlackKeywordRepository;
import com.v1.dgtimes.layer.repository.BookmarkRepository;
import com.v1.dgtimes.layer.repository.KeywordRepository;
import com.v1.dgtimes.layer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    private final KeywordRepository keywordRepository;

    private final UserRepository userRepository;

    private final BlackKeywordRepository blackKeywordRepository;

    @Transactional
    public DefaultResponseDto postBookmarkKeyword(BookmarkRequestDto requestDto, UserDetailImpl userDetail) {

        Keyword keyword = getKeywordAfterValidation(requestDto);

        User user = getUserAfterValidation(userDetail);

        Bookmark bookmark = makeBookmarkWithUserAndKeyword(user, keyword);

        // bookmark 저장
        bookmarkRepository.save(bookmark);

        // 모델 저장
        return DefaultResponseDto.builder()
                .msg("키워드 저장에 성공했습니다.")
                .status(200)
                .build();

    }

    @Transactional
    public List<Bookmark> getBookmarks(String bookmarks, UserDetailImpl userDetail) {
        Long keywordId = keywordRepository.findIdByKeyword(bookmarks);

        User user = userRepository.findById(userDetail.getUser().getId()).get();

        return bookmarkRepository.findByKeywordIdAndUserId(keywordId, user.getId());
    }


    public boolean isBlackKeyword(BookmarkRequestDto requestDto){
        return blackKeywordRepository.existsByBlackKeyword(requestDto.getKeyword());
    }


    private Keyword getKeywordAfterValidation(BookmarkRequestDto requestDto){

        if (requestDto.isValidKeywordBlank()) {
            throw new CustomException(ErrorCode.BOOKMARK_KEYWORD_EMPTY_CODE);
        }

        // 금지된 키워드
        if (isBlackKeyword(requestDto)) {
            throw new CustomException(ErrorCode.BOOKMARK_KEYWORD_FORBIDDEN_CODE);
        }

        return keywordRepository.findByKeyword(requestDto.getKeyword())
                .orElseThrow(() -> new CustomException(ErrorCode.BOOKMARK_KEYWORD_NOT_FOUND));

    }


    private User getUserAfterValidation(UserDetailImpl userDetail){
        // 로그인 검사
        if (userDetail == null) {
            throw new CustomException(ErrorCode.USER_LOGIN_NOT_CODE);
        }

        // 유저 가져온다.
        return userRepository.findById(userDetail.getUserId()).get();
    }


    private Bookmark makeBookmarkWithUserAndKeyword(User user, Keyword keyword){
        // 기존에 등록된 키워드인지 검사
        if (user.isExistKeyword(keyword)) {
            throw new CustomException(ErrorCode.BOOKMARK_KEYWORD_EXIST_USER_CODE);
        }

        return  new Bookmark(user, keyword);
    }

}
