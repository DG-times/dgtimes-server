package com.v2.dgtimes.layer.logging.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/*
설명 : LoggingModel 작성

작성일 : 2022.09.03

마지막 수정한 사람 : 공상욱

*/

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Long")
public class LoggingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date timestamp;

    private String keyword;

    private String includeKeywordList;

    private String excludeKeywordList;



}
