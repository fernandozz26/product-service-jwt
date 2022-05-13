package com.product_service.productservice.repositories;

import java.util.List;

import com.product_service.productservice.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
    @Query(value = "SELECT * FROM product WHERE product_id = (SELECT MAX(product_id) FROM product)", nativeQuery = true)
    public Product getLastProduct();

    @Query(value = "SELECT * FROM product WHERE product_name LIKE %:word%", nativeQuery = true)
    public List<Product> getProductsBySearch(@Param("word") String word);

    @Query(value = "SELECT * FROM product WHERE product_id LIKE :id", nativeQuery = true)
    public List<Product> getProductsBySearchId(@Param("id") Long id);
}
