package com.product_service.productservice.services.cart;

import com.product_service.productservice.entities.Cart;
import com.product_service.productservice.repositories.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartServiceImp  implements CartService{
    @Autowired
    CartRepository cartRepository;

    @Override
    public boolean existCart(Long userId) {
        if(cartRepository.existCart(userId) == 1){
            return true;
        }
        return false;
    }

    @Override
    public void saveNewCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart getCardByUserId(Long userId) {
        Cart cart = cartRepository.getCartByUserId(userId);
        return cart;
    }
    
}
