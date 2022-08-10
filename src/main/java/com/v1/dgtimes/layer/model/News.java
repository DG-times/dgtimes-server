package com.v1.dgtimes.layer.model;

/*
설명 : News Model 구현햇습니다.
    > 22.08.08 - 더미데이터 생성을 위한 1회용 생성자 추가

작성일 : 2022.08.06

마지막 수정한 사람 : 홍우석

Todo -
*/

import com.v1.dgtimes.layer.model.dto.request.NewsRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;
    private String content;
    private String mainUrl;
    private String thumbnailUrl;

    // 컬럼 타입은 'TIMESTAMP'로 저장되는 기능?
    @Column(columnDefinition="TIMESTAMP")
    private Date date;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<KeywordMapping> keywordMappings = new ArrayList<>();

    // Keyword_mapping 연관관계 생성
    public void addKeywordMapping(KeywordMapping keywordMapping) {
        this.keywordMappings.add(keywordMapping);
        // 무한 후프에 빠지지 않기 위해서 작성
        if(keywordMapping.getNews()!=this)
            keywordMapping.updateNews(this);
    }
    // 더미데이터 생성을 위한 1회용 생성자
    public News(NewsRequestDto newsRequestDto) {
        this.title = newsRequestDto.getTitle();
        this.content = newsRequestDto.getContent();
        this.mainUrl = newsRequestDto.getMainUrl();
        this.thumbnailUrl = newsRequestDto.getThumbnailUrl();
        this.date = newsRequestDto.getDate();
    }
}
