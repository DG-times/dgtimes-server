package com.v2.dgtimes.layer.volume.controller;

/*
설명 : VolumeController 작성

작성일 : 2022.09.07

마지막 수정한 사람 : 공상욱

*/

import com.v2.dgtimes.layer.volume.model.Volume;
import com.v2.dgtimes.layer.volume.service.VolumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VolumeController {


    private final VolumeService volumeService;


    @GetMapping("/api/logvolume/{keyword}")
    public ResponseEntity<Volume> getLogVolume(@PathVariable String keyword){
        String s = "news";
        return new ResponseEntity(volumeService.getLogVolume(keyword, s), HttpStatus.OK);
    }

    @GetMapping("/api/newsvolume/{keyword}")
    public ResponseEntity<Volume> getNewsVolume(@PathVariable String keyword){
        String s = "log";
        return new ResponseEntity(volumeService.getNewsVolume(keyword, s), HttpStatus.OK);
    }

}
