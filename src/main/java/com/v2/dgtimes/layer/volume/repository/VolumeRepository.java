package com.v2.dgtimes.layer.volume.repository;

import com.v2.dgtimes.layer.news.model.News;
import com.v2.dgtimes.layer.volume.model.Volume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VolumeRepository extends JpaRepository<News, Long> {

    @Query(value = "select b.date , ifnull(a.count, 0) count from (\n" +
            "\tSELECT DATE_FORMAT(published_date,'%Y-%m-%d') date, COUNT(*) count FROM news n where \n" +
            "    MATCH(n.title, n.content) AGAINST(:keyword IN boolean mode) and n.published_date >= date_add(now(), INTERVAL -6 DAY) GROUP BY date\n" +
            ") as a right join (\n" +
            "    WITH RECURSIVE cte  AS (\n" +
            "\tSELECT date_format(date_add(now(), INTERVAL -5 DAY) ,'%Y-%m-%d') AS date FROM DUAL\n" +
            "\tUNION ALL\n" +
            "\tSELECT date_add(date,INTERVAL 1 DAY) FROM cte\n" +
            "\tWHERE date <= now()\n" +
            ")\n" +
            "SELECT * FROM cte\n" +
            ") as b on a.date = b.date order by date", nativeQuery = true)
    List<Volume> findAllByNewsKeyword(String keyword);

    @Query(value = "select b.date , ifnull(a.count, 0) count from (\n" +
            "\tSELECT DATE_FORMAT(timestamp,'%Y-%m-%d') date, COUNT(*) count FROM search_log s where \n" +
            "    s.keyword = :keyword and s.timestamp >= date_add(now(), INTERVAL -6 DAY) GROUP BY date\n" +
            ") as a right join (\n" +
            "    WITH RECURSIVE cte  AS (\n" +
            "\tSELECT date_format(date_add(now(), INTERVAL -5 DAY) ,'%Y-%m-%d') AS date FROM DUAL\n" +
            "\tUNION ALL\n" +
            "\tSELECT date_add(date,INTERVAL 1 DAY) FROM cte\n" +
            "\tWHERE date < now()\n" +
            ")\n" +
            "SELECT * FROM cte\n" +
            ") as b on a.date = b.date order by date;", nativeQuery = true)
    List<Volume> findAllByLogKeyword(String keyword);
}
