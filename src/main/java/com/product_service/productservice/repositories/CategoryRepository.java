package com.product_service.productservice.repositories;

import com.product_service.productservice.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(nativeQuery = true, value = "select * from category where category_name = :categoryName")
    public Category getByName(@Param("categoryName") String categoryName);
}
