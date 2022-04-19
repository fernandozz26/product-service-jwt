package com.product_service.productservice.services.category;

import java.util.List;

import com.product_service.productservice.entities.Category;

public interface CategoryService {
    // get category
    public List<Category> getAllCategories();
    public Category getCategoryById(Long categoryId);
    public Category getCategoryByName(String categoryName);

    //save update
    public void updateCategory(Category category);
    public void saveCategory(Category category);

    //delete
    public void deleteCategory(Long categoryId);
}
