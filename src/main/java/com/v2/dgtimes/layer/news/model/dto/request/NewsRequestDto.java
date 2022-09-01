package com.v2.dgtimes.layer.news.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDto {
    private String keyword;

    public boolean isNone(){
        if("".equals(keyword) || keyword == null) {
            return true;
        }
        return false;
    }


}
