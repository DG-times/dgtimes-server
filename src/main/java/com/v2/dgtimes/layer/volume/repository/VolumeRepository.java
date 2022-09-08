package com.v2.dgtimes.layer.volume.repository;

/*
설명 : VolumeRepository 작성

작성일 : 2022.09.07

마지막 수정한 사람 : 공상욱

*/


import com.v2.dgtimes.layer.volume.model.Volume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolumeRepository extends JpaRepository<Volume, Long> {

/*
    내가 확인 할 점. 매달 첫날 ~ 끝날까지 timestamp log 를 가져온다.
    이 때 timestamp 의 매달 초 ~ 끝 일을 설정해주는 쿼리문을 짜야한다.
*/

    @Query (value = "select * " +
            "from search_log " +
            "where timestamp >= date_add"
            + "(now(), INTERVAL -1 MONTH)", nativeQuery = true)


    List<Volume> findAllByKeywordAndDat();


}
