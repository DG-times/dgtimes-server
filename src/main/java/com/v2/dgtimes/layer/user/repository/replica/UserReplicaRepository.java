package com.v2.dgtimes.layer.user.repository.replica;

import com.v2.dgtimes.layer.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
설명 : User의 읽기 전용 Repositoiry입니다

작성일 : 2022.09.02

마지막 수정한 사람 : 안상록

*/
public interface UserReplicaRepository extends JpaRepository<User, String> {
}
