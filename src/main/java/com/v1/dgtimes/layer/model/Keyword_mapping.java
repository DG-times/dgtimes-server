package com.v1.dgtimes.layer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
