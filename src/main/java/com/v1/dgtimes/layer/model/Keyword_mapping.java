package com.v1.dgtimes.layer.model;

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
}
