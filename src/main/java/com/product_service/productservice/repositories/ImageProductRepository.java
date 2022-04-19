package com.product_service.productservice.repositories;

import com.product_service.productservice.entities.ImageProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer> {
    
}
