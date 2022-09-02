package com.v2.dgtimes.layer.news.repository.source;

import com.v2.dgtimes.layer.news.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

/*
설명 : 뉴스 Main Repositoiry입니다

작성일 : 2022.09.02

마지막 수정한 사람 : 안상록

*/
public interface NewsSourceRepository extends JpaRepository<News, Long> {
}
