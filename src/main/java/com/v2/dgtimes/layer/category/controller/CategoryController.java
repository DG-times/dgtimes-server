package com.v2.dgtimes.layer.category.controller;

import com.v2.dgtimes.layer.category.model.Category;
import com.v2.dgtimes.layer.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*
설명 : 카테고리 replica 정상 동작 테스트를 위한 임시 Controller

작성일 : 2022.09.02

마지막 수정한 사람 : 안상록

*/
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/api/category/{category}")
    public ResponseEntity postCategory(@PathVariable String category){
        categoryService.postCategory(category);
        return new ResponseEntity("카테고리 추가", HttpStatus.OK);
    }

    @GetMapping("/api/category")
    public ResponseEntity getCategory(){
        List<Category> categories= categoryService.findAll();
        return new ResponseEntity(categories, HttpStatus.OK);
    }
}
