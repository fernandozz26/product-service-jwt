package com.product_service.productservice.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.product_service.productservice.entities.User;
import com.product_service.productservice.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> getByUsername(String username){
        
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void save(User user){
        userRepository.save(user);
    }
    
}
