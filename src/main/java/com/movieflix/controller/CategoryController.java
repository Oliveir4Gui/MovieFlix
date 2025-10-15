package com.movieflix.controller;


import com.movieflix.controller.request.CategoryRequest;
import com.movieflix.controller.response.CategoryResponse;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/movieflix/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<CategoryResponse> getAllCategory(){
     List<Category>categories =categoryService.findAll();
     return categories.stream().map(category -> CategoryMapper.toCategoryResponse(category))
             .toList();

    }

    @PostMapping("/create")
    public CategoryResponse saveCategory(@RequestBody CategoryRequest category){
       Category newCategory = CategoryMapper.toCategory(category);
       Category savedCategory = categoryService.saveCategory(newCategory);
       return CategoryMapper.toCategoryResponse(savedCategory);
    }

    @GetMapping("/{id}")
    public CategoryResponse getByCategoryId(@PathVariable Long id){
        Optional<Category>optcategory = categoryService.findById(id);
        if (optcategory.isPresent())
            return CategoryMapper.toCategoryResponse(optcategory.get());

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){
        categoryService.deleteById(id);

    }
}
