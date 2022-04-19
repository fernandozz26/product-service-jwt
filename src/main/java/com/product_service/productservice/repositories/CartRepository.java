package com.product_service.productservice.repositories;

import com.product_service.productservice.entities.Cart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query(nativeQuery = true, value = "Select * from cart where user_id = :userId")
    public Cart getByUserId(@Param("userId") Long userId);

    

}
