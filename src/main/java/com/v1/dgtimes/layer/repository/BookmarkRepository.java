package com.v1.dgtimes.layer.repository;

import com.v1.dgtimes.layer.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
