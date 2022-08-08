package com.v1.dgtimes.layer.model;

/*
설명 : Keyword Model 구현햇습니다.

작성일 : 2022.08.06

마지막 수정한 사람 : 홍우석

Todo -
*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String keyword;

    @OneToMany(mappedBy = "keyword", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Keyword_mapping> keyword_mappings = new ArrayList<>();

    @OneToMany(mappedBy = "keyword", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bookmark> bookmarks = new ArrayList<>();

    // Keyword_mapping 연관관계 생성
    public void addKeywordMapping(Keyword_mapping keyword_mapping) {
        this.keyword_mappings.add(keyword_mapping);
        // 무한 후프에 빠지지 않기 위해서 작성
        if(keyword_mapping.getKeyword()!=this)
            keyword_mapping.updateKeyword(this);
    }

    // Bookmark 연관관계 생성
    public void addBookmark(Bookmark bookmark) {
        this.bookmarks.add(bookmark);
        // 무한 후프에 빠지지 않기 위해서 작성
        if(bookmark.getKeyword()!=this)
            bookmark.updateKeyword(this);
    }
}
