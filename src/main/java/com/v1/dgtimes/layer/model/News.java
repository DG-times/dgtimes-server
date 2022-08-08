package com.v1.dgtimes.layer.model;

/*
설명 : News Model 구현햇습니다.

작성일 : 2022.08.06

마지막 수정한 사람 : 홍우석

Todo -
*/

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
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;
    private String content;
    private String main_url;
    private String thumbnail_url;

    // 컬럼 타입은 'TIMESTAMP'로 저장되는 기능?
    @Column(columnDefinition="TIMESTAMP")
    private Date date;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Keyword_mapping> keyword_mappings = new ArrayList<>();

    // Keyword_mapping 연관관계 생성
    public void addKeywordMapping(Keyword_mapping keyword_mapping) {
        this.keyword_mappings.add(keyword_mapping);
        // 무한 후프에 빠지지 않기 위해서 작성
        if(keyword_mapping.getNews()!=this)
            keyword_mapping.updateNews(this);
    }
}
