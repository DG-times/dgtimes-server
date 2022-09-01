package com.v2.dgtimes.layer.news.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDto {
    private String keyword;
    private List<String> includeKeywordList;
    private List<String> excludeKeywordList;
    public boolean isNone(){
        if("".equals(keyword) || keyword == null) {
            return true;
        }
        return false;
    }

    public String makeQuery(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(keyword);
        stringBuilder.append(" >");
        includeKeywordList.stream()
                .forEach(include -> stringBuilder.append(" +" + include));
        excludeKeywordList.stream()
                .forEach(exclude -> stringBuilder.append(" -" + exclude));

        return stringBuilder.toString();
    }
}
