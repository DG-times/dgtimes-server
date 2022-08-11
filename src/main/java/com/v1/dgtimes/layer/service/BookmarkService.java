package com.v1.dgtimes.layer.service;

/*
설명 : BookmarkService 수정햇습니다.

작성일 : 2022.08.09

마지막 수정한 사람 : 공상욱

*/

import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.dto.request.BookmarkRequestDto;
import com.v1.dgtimes.layer.model.dto.response.DefaultResponseDto;
import com.v1.dgtimes.layer.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;


    public DefaultResponseDto pstBookmarkKeyword(BookmarkRequestDto requestDto) {
        KeywordDtoValid(requestDto);


        // Valid
        Keyword keyword = new Keyword(requestDto);

        // 저장
        bookmarkRepository.save(keyword);

        // 모델 저장
        return DefaultResponseDto.builder()
                .msg("키워드 저장에 성공했습니다.")
                .status(200)
                .build();
    }


    private void KeywordDtoValid(BookmarkRequestDto requestDto) {
        if (requestDto.isValidKeywordBlank()) {
            throw new RuntimeException("키워드 저장 실패 - 빈 키워드");
        }
        if (requestDto.isValidKeywordBan()) {
            throw new RuntimeException("키워드 저장 실패 - 금지된 키워드");
        }
        if(isExistKeyword(requestDto)){
            throw new RuntimeException("키워드 저장 실패 - 기존에 등록한 키워드");
        }
    }



    private boolean isExistKeyword(BookmarkRequestDto requestDto){
        return bookmarkRepository.existsByKeyword(requestDto.getKeyword());
    }


}
