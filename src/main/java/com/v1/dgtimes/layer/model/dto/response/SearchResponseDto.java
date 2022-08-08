package com.v1.dgtimes.layer.model.dto.response;

/*
설명 : SearchResponseDto 구현
    > 2022.08.08 - SearchService 사용을 위한 생성자 코드 추가

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SearchResponseDto {
    private String title;
    private String content;
    private String thumbnail;
    private String main_url;
    private Date date;
}
