package com.v2.dgtimes.layer.user.repository;

import com.v2.dgtimes.layer.user.model.User;
import com.v2.dgtimes.layer.user.repository.replica.UserReplicaRepository;
import com.v2.dgtimes.layer.user.repository.source.UserSourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
설명 : User의 Repositoiry 입니다.

작성일 : 2022.09.02

마지막 수정한 사람 : 안상록

*/
@Component
@RequiredArgsConstructor
public class UserRepository {
    private final UserReplicaRepository replicaRepository;
    private final UserSourceRepository sourceRepository;

    public void save(User user){
        sourceRepository.save(user);
    }

    public boolean existsById(String userId){
        return replicaRepository.existsById(userId);
    }

    public Optional<User> findById(String userId){
        return replicaRepository.findById(userId);
    }
}
