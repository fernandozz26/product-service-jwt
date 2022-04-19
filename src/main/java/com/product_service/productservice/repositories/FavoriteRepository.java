package com.product_service.productservice.repositories;

import java.util.List;

import com.product_service.productservice.entities.Favorite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    @Query(nativeQuery = true, value = "select * from favorite where user_id = :userId")
    public List<Favorite> getByUserId(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "select * from favorite where user_id = :userId and product_id = :productId")
    public Favorite getByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}
