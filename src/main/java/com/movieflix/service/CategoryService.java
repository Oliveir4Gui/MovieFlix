package com.movieflix.service;


import com.movieflix.entity.Category;
import com.movieflix.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
        }

    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }

    public  Optional<Category> updateCategory(Long id, Category category){
        Optional<Category> byId = categoryRepository.findById(id);
        if(byId.isPresent()){
            Category categoryToUpdate = byId.get();
            categoryToUpdate.setName(category.getName());
            categoryRepository.save(categoryToUpdate);
            return Optional.of(categoryToUpdate);

        }
        return Optional.empty();
    }


    }

