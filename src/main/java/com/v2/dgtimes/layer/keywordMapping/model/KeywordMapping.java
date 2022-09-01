package com.v2.dgtimes.layer.keywordMapping.model;

import com.v2.dgtimes.layer.keyword.model.Keyword;
import com.v2.dgtimes.layer.news.model.News;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="news_id")
    private News news;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="keyword_id")
    private Keyword keyword;

    private Long count;

    // 연관 관계 설정 - Keyword
    public void updateKeyword(Keyword keyword) {
        this.keyword = keyword;
        // 무한 루프에 빠지지 않기 위해 작성
        if(!keyword.getKeywordMappings().contains(this))
            keyword.addKeywordMapping(this);
    }

    // 연관 관계 설정 - News
    public void updateNews(News news) {
        this.news = news;
        // 무한 루프에 빠지지 않기 위해 작성
        if(!news.getKeywordMappings().contains(this))
            news.addKeywordMapping(this);
    }

    // Keyword, News 동시 등록을 위한 연관 관계 설정
    public void updateKeywordNews(Keyword keyword, News news) {
        this.keyword = keyword;
        this.news = news;
        if(!keyword.getKeywordMappings().contains(this))
            keyword.addKeywordMapping(this);
        if(!news.getKeywordMappings().contains(this))
            news.addKeywordMapping(this);
    }

}
