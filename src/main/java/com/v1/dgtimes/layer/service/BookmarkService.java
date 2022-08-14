package com.v1.dgtimes.layer.service;

/*
설명 : BookmarkService 수정햇습니다.

작성일 : 2022.08.12

마지막 수정한 사람 : 공상욱

*/

import com.v1.dgtimes.config.security.userdetail.UserDetailImpl;
import com.v1.dgtimes.layer.model.Bookmark;
import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.User;
import com.v1.dgtimes.layer.model.dto.request.BookmarkRequestDto;
import com.v1.dgtimes.layer.model.dto.response.DefaultResponseDto;
import com.v1.dgtimes.layer.repository.BookmarkRepository;
import com.v1.dgtimes.layer.repository.KeywordRepository;
import com.v1.dgtimes.layer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    private final KeywordRepository keywordRepository;

    private final UserRepository userRepository;

    @Transactional
    public DefaultResponseDto postBookmarkKeyword(BookmarkRequestDto requestDto, UserDetailImpl userDetail) {


        //빈 키워드인지 검사
        if (requestDto.getKeyword() == null || requestDto.getKeyword().equals("")) {
            throw new RuntimeException("키워드 저장 실패 - 빈 키워드");
        }


        // 금지된 키워드
        if (requestDto.getKeyword().equals("야한거")) {
            throw new RuntimeException("키워드 저장 실패 - 금지된 키워드");
        }


        Keyword keyword = keywordRepository.findByKeyword(requestDto.getKeyword())
                .orElseThrow(() -> new RuntimeException("키워드 저장 실패 - 일단 실패"));


        // 로그인 검사
        if (userDetail == null) {
            throw new RuntimeException("키워드 저장 실패 - 로그인 되지 않음");
        }


        // 유저 가져온다.
        User user = userRepository.findById(userDetail.getUser().getId()).get();


        // 기존에 등록된 키워드인지 검사
        if (user.getBookmarks().stream()
                .anyMatch(x -> x.getKeyword().getKeyword().equals(requestDto.getKeyword()))) {
            throw new RuntimeException("키워드 저장 실패 - 기존에 등록한 키워드");
        }


        // Valid
        Bookmark bookmark = new Bookmark(userDetail.getUser(), keyword);


        // bookmark 저장
        bookmarkRepository.save(bookmark);


        // 모델 저장
        return DefaultResponseDto.builder()
                .msg("키워드 저장에 성공했습니다.")
                .status(200)
                .build();

    }

}
