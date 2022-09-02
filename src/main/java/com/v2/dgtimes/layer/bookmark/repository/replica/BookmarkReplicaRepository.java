package com.v2.dgtimes.layer.bookmark.repository.replica;

import com.v2.dgtimes.layer.bookmark.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkReplicaRepository extends JpaRepository<Bookmark, Long> {

}
