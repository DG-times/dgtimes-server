package com.v2.dgtimes.layer.news.model;

import com.v2.dgtimes.layer.category.model.Category;
import com.v2.dgtimes.layer.keywordMapping.model.KeywordMapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String writer;
    private String publisher;
    @Column(columnDefinition="TIMESTAMP")
    private Date publishedDate;
    @ManyToOne()
    private Category category;
    private String tag;
    private String thumbnailUrl;
    private String newsUrl;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<KeywordMapping> keywordMappings = new ArrayList<>();

    public void addKeywordMapping(KeywordMapping keywordMapping) {
        this.keywordMappings.add(keywordMapping);
        // 무한 후프에 빠지지 않기 위해서 작성
        if(keywordMapping.getNews()!=this)
            keywordMapping.updateNews(this);
    }
}
