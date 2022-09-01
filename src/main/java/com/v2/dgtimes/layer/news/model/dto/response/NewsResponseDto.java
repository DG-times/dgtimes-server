package com.v2.dgtimes.layer.news.model.dto.response;

import com.v2.dgtimes.layer.news.model.News;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class NewsResponseDto {
    private String title;
    private String content;
    private String thumbnailUrl;
    private String mainUrl;
    private Date publishedDate;
    private String tag;

    // Service 뉴스데이터 관련 생성자
    public NewsResponseDto(News news) {
        this.title = news.getTitle();
        this.content = news.getContent();
        this.thumbnailUrl = news.getThumbnailUrl();
        this.mainUrl = news.getNewsUrl();
        this.publishedDate = news.getPublishedDate();
        this.tag = news.getTag();
    }
}

