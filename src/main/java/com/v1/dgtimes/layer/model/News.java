package com.v1.dgtimes.layer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
}
