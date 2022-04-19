package com.product_service.productservice.security.service;

import org.springframework.stereotype.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.product_service.productservice.entities.User;
import com.product_service.productservice.security.entities.*;
@Service
public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userService.getByUsername(username).get();
        
        return MainUser.build(user);
    }
    
}
