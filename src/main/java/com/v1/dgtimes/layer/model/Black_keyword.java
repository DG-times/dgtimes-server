package com.v1.dgtimes.layer.model;


/*
설명 : Black_keyword Model 구현햇습니다. (공상욱)
    > 22.08.08 - 더미데이터 생성을 위한 생성자 작성 및 black_keyword -> blackKeyword 수정 (홍우석)

작성일 : 2022.08.06

마지막 수정한 사람 : 홍우석

*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Black_keyword {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String blackKeyword;

    // 더미데이터 생성을 위한 생성자
    public Black_keyword(String blackKeyword) {
        this.blackKeyword = blackKeyword;
    }
}
