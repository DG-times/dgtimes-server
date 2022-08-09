package com.v1.dgtimes.layer.service;

/*
설명 : BookmarkService 구현햇습니다.

작성일 : 2022.08.08

마지막 수정한 사람 : 공상욱

*/

import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.dto.request.BookmarkRequestDto;
import com.v1.dgtimes.layer.model.dto.response.SearchResponseDto;
import com.v1.dgtimes.layer.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;


    //Bookmark 키워드 저장 성공
    @Transactional
    public SearchResponseDto pstBookmarkKeyword(BookmarkRequestDto bookmarkRequestDto) {
        Keyword keyword  = bookmarkRequestDto.getKeyword();
        if ("".equals(keyword) || keyword == null) {
            throw new RuntimeException("키워드를 입력해주세요.");
        }
        bookmarkRepository.save(keyword);
        return null;
    }




    //Bookmark 실패(공란)





    //Bookmark 실패(로그인 되지 않았음 | UserBookmark)




    //Bookmark 실패(중복 키워드 | OverlapBookmark)
    public ResponseEntity<?> OverlapBookmark(String keyword) {
        if (bookmarkRepository.findByKeyword(keyword).isPresent())
            return new ResponseEntity<>("중복 키워드 입니다.", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>("키워드가 등록 되었습니다.", HttpStatus.OK);
    }




    //Bookmark 실패(금지어 검색 | BlackKeyword)





    //Bookmark 실패(그 외)





}
