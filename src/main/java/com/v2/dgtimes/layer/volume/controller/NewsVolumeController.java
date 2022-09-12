package com.v2.dgtimes.layer.volume.controller;

import com.v2.dgtimes.layer.volume.model.NewsVolume;
import com.v2.dgtimes.layer.volume.service.NewsVolumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewsVolumeController {

    private final NewsVolumeService newsVolumeService;

    @GetMapping("/api/newsvolume/{keyword}")
    public ResponseEntity<NewsVolume> getNewsVolume(@PathVariable String keyword){
        return new ResponseEntity(newsVolumeService.getNewsVolume(keyword), HttpStatus.OK);
    }
}
