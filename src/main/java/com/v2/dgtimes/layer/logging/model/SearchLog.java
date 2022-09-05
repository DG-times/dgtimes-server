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
@Table(name = "search_log")
public class SearchLog extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_id;

    private String keyword;

    private String includeKeywordList;

    private String excludeKeywordList;



}
