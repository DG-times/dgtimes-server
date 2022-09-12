package com.v2.dgtimes.layer.news.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "test-sequence-generator")
    @GenericGenerator(
            name = "test-sequence-generator",
            strategy = "sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = SequenceStyleGenerator.DEF_SEQUENCE_NAME),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INITIAL_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1000"),
                    @org.hibernate.annotations.Parameter(name = AvailableSettings.PREFERRED_POOLED_OPTIMIZER, value = "pooled-lo")
            }
    )
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String writer;
    private String publisher;
    @Column(columnDefinition = "TIMESTAMP")
    private Timestamp publishedDate;
    private long category;
    private String thumbnailUrl;
    private String mainUrl;
}
