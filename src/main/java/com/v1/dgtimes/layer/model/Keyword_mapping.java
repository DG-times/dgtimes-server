package com.v1.dgtimes.layer.model;

/*
설명 : Keyword_mapping Model 구현햇습니다.

작성일 : 2022.08.06

마지막 수정한 사람 : 홍우석

Todo -
*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.Store;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Keyword_mapping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="News_id")
    private News news;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Keyword_id")
    private Keyword keyword;

    // 연관 관계 설정 - Keyword
    public void updateKeyword(Keyword keyword) {
        this.keyword = keyword;
        // 무한 루프에 빠지지 않기 위해 작성
        if(!keyword.getKeyword_mappings().contains(this))
            keyword.addKeywordMapping(this);
    }

    // 연관 관계 설정 - News
    public void updateNews(News news) {
        this.news = news;
        // 무한 루프에 빠지지 않기 위해 작성
        if(!news.getKeyword_mappings().contains(this))
            news.addKeywordMapping(this);
    }

    // Keyword, News 동시 등록을 위한 연관 관계 설정
    public void updateKeywordNews(Keyword keyword, News news) {
        this.keyword = keyword;
        this.news = news;
        if(!keyword.getKeyword_mappings().contains(this))
            keyword.addKeywordMapping(this);
        if(!news.getKeyword_mappings().contains(this))
            news.addKeywordMapping(this);
    }
}
