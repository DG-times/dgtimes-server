package com.v2.dgtimes.layer.logging.repository;

import com.v2.dgtimes.layer.logging.model.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
설명 : LoggingRepository 작성

작성일 : 2022.09.03

마지막 수정한 사람 : 공상욱

*/

public interface SearchLogRepository extends JpaRepository<SearchLog, String> {
    @Query(value = "select * " +
            "from search_log " +
            "where timestamp >= date_add" +
            "(now(), INTERVAL -1 HOUR)", nativeQuery = true)
    List<SearchLog> findAllById();
}
