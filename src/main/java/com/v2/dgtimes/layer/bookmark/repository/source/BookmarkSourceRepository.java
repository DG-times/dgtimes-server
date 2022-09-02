package com.v2.dgtimes.layer.bookmark.repository.source;

import com.v2.dgtimes.layer.bookmark.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkSourceRepository extends JpaRepository<Bookmark, Long> {
}
