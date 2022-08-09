package com.v1.dgtimes.layer.repository;

import com.v1.dgtimes.layer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findAllById(String userId);
}
