package com.product_service.productservice.repositories;

import java.util.Optional;

import com.product_service.productservice.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM user where username = :username")
    public User findByusername(@Param("username") String username);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String emeal);
}
