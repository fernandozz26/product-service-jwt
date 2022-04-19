package com.product_service.productservice.repositories;

import java.util.List;

import com.product_service.productservice.entities.CategoryProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductRepository extends JpaRepository<CategoryProduct, Integer> {
    @Query(nativeQuery = true, value = "select * from category_product where category_id = :categoryId")
    public List<CategoryProduct> getByCategoryId(@Param("categoryId") Long categoryId);
}
