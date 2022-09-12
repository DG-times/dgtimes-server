package com.v2.dgtimes.layer.volume.service;

import com.v2.dgtimes.layer.volume.model.Volume;
import com.v2.dgtimes.layer.volume.repository.VolumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/*
설명 : 검색 한 키워드의 뉴스 언급량을 측정하는 Service입니다.
     - cach 사용

작성일 : 2022.09.12

마지막 수정한 사람 : 안상록

*/
@Service
@RequiredArgsConstructor
public class VolumeService {

    private final VolumeRepository volumeRepository;

    @Cacheable(value = "List<Volume>", key = "#s.concat(#keyword)", cacheManager = "cacheManager")
    public List<Volume> getNewsVolume(String keyword, String s) {

        return volumeRepository.findAllByNewsKeyword(keyword);

    }

    @Cacheable(value = "List<Volume>", key = "#s.concat(#keyword)", cacheManager = "cacheManager")
    public List<Volume> getLogVolume(String keyword, String s) {

        return volumeRepository.findAllByLogKeyword(keyword);
    }
}
