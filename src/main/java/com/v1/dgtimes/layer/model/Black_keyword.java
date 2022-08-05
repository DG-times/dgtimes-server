package com.v1.dgtimes.layer.model;


/*
설명 : Black_keyword Model 구현햇습니다.

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
public class Black_keyword {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private  String black_keyword;

}
