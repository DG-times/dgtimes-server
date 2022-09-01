package com.v2.dgtimes.layer.user.repository;

import com.v2.dgtimes.layer.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
