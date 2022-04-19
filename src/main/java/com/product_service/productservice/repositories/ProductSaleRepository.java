package com.product_service.productservice.repositories;

import com.product_service.productservice.entities.ProductSale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Integer> {
    
}
