package com.v1.dgtimes.layer.model.dto.request;

/*
설명 : NewsRequestDto 구현
    >

작성일 : 2022.08.08

마지막 수정한 사람 : 홍우석

Todo -
*/

import com.v1.dgtimes.layer.model.News;
import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDto {
    private String title;
    private String content;
    private String mainUrl;
    private String thumbnailUrl;
    private Date date;

    public NewsRequestDto(String title, String content, String mainUrl, String thumbnailUrl) {
        this.title = title;
        this.content = content;
        this.mainUrl = mainUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public News toNews(){
        return News.builder()
                .title(this.title)
                .content(this.content)
                .mainUrl(this.mainUrl)
                .thumbnailUrl(this.thumbnailUrl)
                .date(this.date)
                .build();
    }
}
