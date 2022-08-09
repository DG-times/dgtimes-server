package com.v1.dgtimes.layer.model.dto.response;

/*
설명 : SearchResponseDto 구현
    > 2022.08.08 - SearchService 사용을 위한 생성자 코드 추가

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import com.v1.dgtimes.layer.model.News;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class SearchResponseDto {
    private String title;
    private String content;
    private String thumbnail;
    private String main_url;
    private Date date;

    // Service 뉴스데이터 관련 생성자
    public SearchResponseDto(News news) {
        this.title = news.getTitle();
        this.content = news.getContent();
        this.thumbnail = news.getThumbnail_url();
        this.main_url = news.getMain_url();
        this.date = news.getDate();
    }
}
