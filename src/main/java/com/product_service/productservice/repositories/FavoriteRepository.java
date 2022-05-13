package com.product_service.productservice.repositories;

import java.util.List;

import com.product_service.productservice.entities.Favorite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query(nativeQuery = true, value = "select * from favorite where user_id = :userId")
    public List<Favorite> getByUserId(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "select * from favorite where user_id = :userId and product_id = :productId")
    public Favorite getByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query(nativeQuery = true, value = "select case when count(favorite_id)> 0 then true else false end from favorite where user_id = :userId and product_id = :productId")
    public int existsFavoriteByProductId(@Param("userId") Long userId ,@Param("productId") Long productId);

    @Modifying
    @Query(nativeQuery = true, value = "delete from favorite where favorite_id = :favoriteId")
    public void deleteFavoriteById( @Param("favoriteId") Long favoriteId);
}
