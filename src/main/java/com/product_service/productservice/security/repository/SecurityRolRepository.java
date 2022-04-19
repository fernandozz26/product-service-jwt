package com.product_service.productservice.security.repository;

import java.util.Optional;

import com.product_service.productservice.security.entities.Rol;
import com.product_service.productservice.security.enums.RolName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRolRepository extends JpaRepository<Rol, Long> {
    
    public Optional<Rol> findByRolName(RolName rolName);
}
