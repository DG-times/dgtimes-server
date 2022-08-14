package com.v1.dgtimes.layer.model.dto.request;

/*
설명 : BookmarkRequestDto 구현햇습니다.

작성일 : 2022.08.09

마지막 수정한 사람 : 공상욱

*/

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkRequestDto {

    private String keyword;
//    private String user;


    // 공란
//    public boolean isValidKeywordBlank(){return this.keyword.equals("");}

    // 금지된 키워드
//    public boolean isValidKeywordBan(){return this.keyword.equals("야한거");}

    // 로그인 되지 않음
//    public boolean isValidUserNotLogin(){return this.user.isEmpty();}

}
