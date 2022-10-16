package com.v2.dgtimes.layer.category.service;

import com.v2.dgtimes.layer.category.model.Category;
import com.v2.dgtimes.layer.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
설명 : 카테고리 replica 정상 동작 테스트를 위한 임시 service
    -@Transactional(readOnly = true) 설정

작성일 : 2022.09.04

마지막 수정한 사람 : 안상록

*/
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public void postCategory(String value) {
        Category category = Category.builder()
                .value(value)
                .build();
        repository.save(category);
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return repository.findAll();
    }
}
