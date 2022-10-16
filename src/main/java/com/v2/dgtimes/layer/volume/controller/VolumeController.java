package com.v2.dgtimes.layer.volume.controller;

/*
설명 : VolumeController 작성

작성일 : 2022.09.07

마지막 수정한 사람 : 공상욱

*/

import com.v2.dgtimes.layer.volume.model.Volume;
import com.v2.dgtimes.layer.volume.model.VolumeDto;
import com.v2.dgtimes.layer.volume.service.VolumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VolumeController {

    private final VolumeService volumeService;

    @GetMapping("/api/keyword/volume/mention/{keyword}")
    public ResponseEntity<List<VolumeDto>> getMentionVolume(@PathVariable String keyword){
        String s = "news";
        return new ResponseEntity(volumeService.getMentionVolume(keyword, s), HttpStatus.OK);
    }

    @GetMapping("/api/keyword/volume/search/{keyword}")
    public ResponseEntity<VolumeDto> getSearchVolume(@PathVariable String keyword){
        String s = "log";
        return new ResponseEntity(volumeService.getSearchVolume(keyword, s), HttpStatus.OK);
    }


}
