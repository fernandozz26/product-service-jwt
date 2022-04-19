package com.product_service.productservice.security.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import com.product_service.productservice.security.entities.Rol;
import com.product_service.productservice.security.enums.RolName;
import com.product_service.productservice.security.repository.SecurityRolRepository;

@Service
@Transactional
public class RolService {

    @Autowired
    SecurityRolRepository rolRepository;

    public Optional<Rol> getByRolName (RolName rolName){
        return rolRepository.findByRolName(rolName);
    }

}
