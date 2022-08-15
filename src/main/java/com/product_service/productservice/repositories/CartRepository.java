package com.product_service.productservice.repositories;

import com.product_service.productservice.entities.Cart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query(nativeQuery = true, value = "Select MAX(cart_id) from cart where user_id = :userId")
    public Long getCartIdByUserId(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "select * from cart where user_id = :userId")
    public Cart getCartByUserId(@Param("userId") Long userId);

    @Query(nativeQuery = true, 
    value = "select case when count(product_id) > 0 then true else false end from product_cart where cart_id = (select cart_id from cart where user_id = :userId) and product_id = :productId")
    public int existsCartByProductId(@Param("userId") Long userId ,@Param("productId") Long productId);

    @Query(nativeQuery = true, value = "select case when count(cart_id) > 0 then true else false end from cart where user_id = :userId")
    public int existCart(@Param("userId") Long userId);

}
