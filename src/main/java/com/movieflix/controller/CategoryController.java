package com.movieflix.controller;


import com.movieflix.controller.request.CategoryRequest;
import com.movieflix.controller.response.CategoryResponse;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/movieflix/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategory(){
     List<Category>categories =categoryService.findAll();
     List<CategoryResponse> list =  categories.stream().map(CategoryMapper::toCategoryResponse)
             .toList();
     return  ResponseEntity.ok().body(list);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest category){
       Category newCategory = CategoryMapper.toCategory(category);
       Category savedCategory = categoryService.saveCategory(newCategory);
      return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(savedCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getByCategoryId(@PathVariable Long id){
       return categoryService.findById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteCategoryById(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest category){
        return categoryService.updateCategory(id, CategoryMapper.toCategory(category))
                .map(category1 -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category1)))
                .orElse(ResponseEntity.notFound().build());
    }
}
