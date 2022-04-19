package com.product_service.productservice.repositories;

import com.product_service.productservice.entities.Sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    
}
