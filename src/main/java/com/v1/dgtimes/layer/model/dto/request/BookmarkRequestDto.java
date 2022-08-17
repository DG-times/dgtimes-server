package com.v1.dgtimes.layer.model.dto.request;

/*
설명 : BookmarkRequestDto 구현햇습니다.

작성일 : 2022.08.09

마지막 수정한 사람 : 공상욱

*/

import com.v1.dgtimes.layer.repository.BlackKeywordRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkRequestDto {

    private String keyword;

    //빈 키워드인지 검사
    public boolean isValidKeywordBlank(){
        return this.keyword.equals("") || this.keyword == null;
    }

}
