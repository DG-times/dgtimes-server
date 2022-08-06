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
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Keyword_id")
    private Keyword keyword;

    // 연관 관계 설정 - Keyword
    public void updateKeyword(Keyword keyword) {
        this.keyword = keyword;
        // 무한 루프에 빠지지 않기 위해 작성
        if(!keyword.getBookmarks().contains(this))
            keyword.addBookmark(this);
    }








}
