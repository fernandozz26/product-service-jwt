package com.product_service.productservice.controller;

import java.util.List;

import com.product_service.productservice.entities.Category;
import com.product_service.productservice.services.category.CategoryServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryServiceImp categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        if(categories.equals(null) || categories == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(categories);
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Category> getAllCategoryById(@PathVariable("categoryId") Long categoryId){
        Category category = categoryService.getCategoryById(categoryId);
        if(category.equals(null) || category == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(category);
        }
    }
}
