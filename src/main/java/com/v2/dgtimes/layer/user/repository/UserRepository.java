package com.v2.dgtimes.layer.user.repository;

import com.v2.dgtimes.layer.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
설명 : User의 Repositoiry 입니다.

작성일 : 2022.09.04

마지막 수정한 사람 : 안상록

*/

public interface UserRepository extends JpaRepository<User, String> {

}

