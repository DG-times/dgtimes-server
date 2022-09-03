package com.v2.dgtimes.layer.logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/*
설명 : LoggingRepository 작성

작성일 : 2022.09.03

마지막 수정한 사람 : 공상욱

*/

public interface LoggingRepository extends JpaRepository<Long, String> {
}
