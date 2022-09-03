package com.v2.dgtimes.layer.bookmark.model;

import com.v2.dgtimes.layer.news.model.dto.request.NewsRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bookmark")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "include_keyword_list")
    private String includeKeywordList;
    @Column(name = "exclude_keyword_list")
    private String excludeKeywordList;

    public List<String> getIncludeKeywordList() {
        return Arrays.asList(includeKeywordList.split(","));
    }

    public List<String> getExcludeKeywordList() {
        return Arrays.asList(excludeKeywordList.split(","));
    }

    public void setIncludeKeywordList(List<String> keywordList) {
        this.includeKeywordList = String.join(",", keywordList);
    }
    public void setExcludeKeywordList(List<String> keywordList) {
        this.excludeKeywordList = String.join(",", keywordList);
    }

    public void update(NewsRequestDto newsRequestDto){
        List<String> includeKeywordList = newsRequestDto.getIncludeKeywordList();
        List<String> excludeKeywordList = newsRequestDto.getExcludeKeywordList();
        this.setIncludeKeywordList(includeKeywordList);
        this.setExcludeKeywordList(excludeKeywordList);
    }
}
