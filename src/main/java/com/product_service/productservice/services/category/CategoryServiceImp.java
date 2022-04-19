package com.product_service.productservice.services.category;

import java.util.List;

import com.product_service.productservice.entities.Category;
import com.product_service.productservice.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        Category category = categoryRepository.getById(categoryId);
        return category;
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        Category category = categoryRepository.getByName(categoryName);
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
        
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        
    }
    
}
