package com.v2.dgtimes.layer.volume.model.dto.response;

/*
설명 : VolumeResponseDto 작성

작성일 : 2022.09.07

마지막 수정한 사람 : 공상욱

*/

import com.v2.dgtimes.layer.volume.model.Volume;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class VolumeResponseDto {

    private Date date;

    private String count;




    private VolumeResponseDto(Volume volume){
        this.date = volume.getDate();
        this.count = volume.getCount();
    }
}
