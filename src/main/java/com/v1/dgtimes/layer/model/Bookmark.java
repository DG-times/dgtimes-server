package com.v1.dgtimes.layer.model;

/*
설명 : Bookmark Model 구현햇습니다.

작성일 : 2022.08.06

마지막 수정한 사람 : 공상욱

*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "keyword_id")
    private Keyword keyword;


    public Bookmark(User user, Keyword keyword) {
        this.user = user;
        this.keyword = keyword;
    }


    // 연관 관계 설정 - Keyword
    public void updateKeyword(Keyword keyword) {
        this.keyword = keyword;
        // 무한 루프에 빠지지 않기 위해 작성
        if(!keyword.getBookmarks().contains(this))
            keyword.addBookmark(this);
    }








}
