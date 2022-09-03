package com.v2.dgtimes.layer.bookmark.repository;


import com.v2.dgtimes.layer.bookmark.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

/*
설명 :  BookMark Repositoiry입니다

작성일 : 2022.09.03

마지막 수정한 사람 : 안상록

*/
//@Component
//@RequiredArgsConstructor
//public class BookmarkRepository{
//
//    private final BookmarkReplicaRepository replicaRepository;
//    private final BookmarkSourceRepository sourceRepository;
//
//    public Bookmark save(Bookmark bookmark){
//        return sourceRepository.save(bookmark);
//    }
//
//}
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
