package com.product_service.productservice.repositories;

import java.util.List;

import com.product_service.productservice.entities.ProductCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart,Long> {
    @Query(nativeQuery = true, value = "select * from product_cart where cart_id = :cartId")
    public List<ProductCart> getByCartId(@Param(value= "cartId") Long cartId);

    @Query(nativeQuery = true, value = "select * from product_cart where cart_id = :cartId and product_id = :productId")
    public ProductCart getByUserIdAndProductId(@Param( value = "cartId") Long cartId, @Param("productId") Long productId);

    
}
