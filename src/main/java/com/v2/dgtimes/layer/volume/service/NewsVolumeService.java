package com.v2.dgtimes.layer.volume.service;

import com.v2.dgtimes.layer.volume.model.NewsVolume;
import com.v2.dgtimes.layer.volume.repository.NewsVolumeRepository;
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
public class NewsVolumeService {

    private final NewsVolumeRepository newsVolumeRepository;

    @Cacheable(value = "List<NewsVolume>", key = "#keyword", cacheManager = "cacheManager")
    public List<NewsVolume> getNewsVolume(String keyword) {

        return newsVolumeRepository.findAllByKeyword(keyword);

    }
}
