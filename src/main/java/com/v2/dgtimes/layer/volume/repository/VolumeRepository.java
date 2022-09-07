package com.v2.dgtimes.layer.volume.repository;

/*
설명 : VolumeRepository 작성

작성일 : 2022.09.07

마지막 수정한 사람 : 공상욱

*/


import com.v2.dgtimes.layer.volume.model.Volume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolumeRepository extends JpaRepository<Volume, String> {
}
