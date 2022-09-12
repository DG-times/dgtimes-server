package com.v2.dgtimes.layer.volume.service;

import com.v2.dgtimes.layer.volume.model.Volume;
import com.v2.dgtimes.layer.volume.model.VolumeDto;
import com.v2.dgtimes.layer.volume.repository.VolumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Cacheable(value = "List<VolumeDto>", key = "#s.concat(#keyword)", cacheManager = "cacheManager")
    @Transactional(readOnly = true)
    public List<VolumeDto> getNewsVolume(String keyword, String s) {

        return volumeRepository.findAllByNewsKeyword(keyword).stream()
                .map(VolumeDto::new)
                .collect(Collectors.toList());

    }

    @Cacheable(value = "List<Volume>", key = "#s.concat(#keyword)", cacheManager = "cacheManager")
    @Transactional(readOnly = true)
    public List<Volume> getLogVolume(String keyword, String s) {

        return volumeRepository.findAllByLogKeyword(keyword);
    }
}
