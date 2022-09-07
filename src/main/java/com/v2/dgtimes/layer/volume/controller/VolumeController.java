package com.v2.dgtimes.layer.volume.controller;

/*
설명 : VolumeController 작성

작성일 : 2022.09.07

마지막 수정한 사람 : 공상욱

*/

import com.v2.dgtimes.layer.volume.service.VolumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VolumeController {

    private final VolumeService volumeService;


//    @GetMapping("/api/keyword/search/volume")
//    private

}
