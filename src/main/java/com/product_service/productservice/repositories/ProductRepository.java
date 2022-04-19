package com.product_service.productservice.repositories;

import com.product_service.productservice.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
    @Query(value = "SELECT * FROM product WHERE product_id = (SELECT MAX(product_id) FROM product)", nativeQuery = true)
    public Product getLastProduct();
}
