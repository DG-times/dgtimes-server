package com.v2.dgtimes.layer.volume.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VolumeDto {
    private String date;
    private Integer count;

    public VolumeDto(Volume volume){
        this.date = volume.getDate();
        this.count = volume.getCount();
    }
}
