package com.v1.dgtimes.layer.service;


import com.v1.dgtimes.layer.model.Keyword;
import com.v1.dgtimes.layer.model.dto.request.KeywordRequestDto;
import com.v1.dgtimes.layer.repository.BookmarkRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.mockito.Mockito.when;

/*
설명 : BookmarkServiceTest 작성햇습니다.

작성일 : 2022.08.12

마지막 수정한 사람 : 공상욱

*/

@ExtendWith(MockitoExtension.class)
public class BookmarkServiceTest {

    @Mock
    public BookmarkRepository bookmarkRepository;

    @InjectMocks
    private BookmarkService bookmarkService;


    @Test
    @DisplayName("키워드 저장 실패 - 빈 키워드")
    public void test1() {

        // Given


        // When

        // Then
    }


    @Test
    @DisplayName("키워드 저장 실패 - 로그인 되지 않음")
    public void test2() {
        // Given

        // When

        // Then
    }


    @Test
    @DisplayName("키워드 저장 실패 - 기존에 등록한 키워드")
    public void test3() {
        // Given

        // When


        // Then
    }


    @Test
    @DisplayName("키워드 저장 실패 - 금지된 키워드")
    public void test4() {
        // Given

        // When

        // Then
    }


    @Test
    @DisplayName("키워드 저장에 성공했습니다.")
    public void test5() {
        // Given

        // When

        // Then
    }
}
