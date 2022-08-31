package com.v2.dgtimes.layer.keyword.model;

import com.v2.dgtimes.layer.keywordMapping.model.KeywordMapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String keyword;

    @OneToMany(mappedBy = "keyword", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<KeywordMapping> keywordMappings = new ArrayList<>();

    // Keyword_mapping 연관관계 생성
    public void addKeywordMapping(KeywordMapping keywordMapping) {
        this.keywordMappings.add(keywordMapping);
        // 무한 후프에 빠지지 않기 위해서 작성
        if(keywordMapping.getKeyword()!=this)
            keywordMapping.updateKeyword(this);
    }
}
