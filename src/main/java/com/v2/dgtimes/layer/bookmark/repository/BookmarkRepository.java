package com.v2.dgtimes.layer.bookmark.repository;

import com.v2.dgtimes.layer.bookmark.model.Bookmark;
import com.v2.dgtimes.layer.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
