package com.v2.dgtimes.layer.category.repository;

import com.v2.dgtimes.layer.category.model.Category;
import com.v2.dgtimes.layer.category.repository.replica.CategoryReplicaRepository;
import com.v2.dgtimes.layer.category.repository.source.CategorySourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/*
설명 : 카테고리 Repositoiry 입니다.

작성일 : 2022.09.02

마지막 수정한 사람 : 안상록

*/
@Component
@RequiredArgsConstructor
public class CategoryRepository {

    private final CategoryReplicaRepository replicaRepository;
    private final CategorySourceRepository sourceRepository;

    public List<Category> findAll(){
        return replicaRepository.findAll();
    }

    public Category save(Category category){
        return sourceRepository.save(category);
    }
}
