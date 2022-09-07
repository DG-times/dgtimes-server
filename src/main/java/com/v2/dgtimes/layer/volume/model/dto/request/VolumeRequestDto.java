package com.v2.dgtimes.layer.volume.model.dto.request;

/*
설명 : VolumeRequestDto 작성

작성일 : 2022.09.07

마지막 수정한 사람 : 공상욱

*/

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VolumeRequestDto {

    private String keyword;

    @Builder
    public VolumeRequestDto(String keyword) {
        this.keyword = keyword;
    }
}
