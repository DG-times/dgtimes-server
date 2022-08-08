package com.v1.dgtimes.layer.model.dto.request;

/*
설명 : NewsRequestDto 구현
    >

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
public class NewsRequestDto {
    private String title;
    private String content;
    private String main_url;
    private String thumbnail_url;
    private Date date;
}
