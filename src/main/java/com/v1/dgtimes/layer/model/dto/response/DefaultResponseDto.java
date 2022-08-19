package com.v1.dgtimes.layer.model.dto.response;

/*
설명 : DefaultResponseDto 구현햇습니다.

작성일 : 2022.08.08

마지막 수정한 사람 : 공상욱

*/

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DefaultResponseDto {
        private String msg;
        private int status;
}
