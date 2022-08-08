package com.v1.dgtimes.layer.model.dto.request;

/*
설명 : KeywordRequestDto 구현
    >

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KeywordRequestDto {
    private String keyword;

    public KeywordRequestDto(String keyword) {
        this.keyword = keyword;
    }
}
