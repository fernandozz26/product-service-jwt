package com.product_service.productservice.services.cart;

import com.product_service.productservice.entities.Cart;

public interface CartService {

    // check
    public boolean existCart(Long userId);

    // get

    public Cart getCardByUserId(Long userId);
    
    // save
    public void saveNewCart(Cart cart);

    // delete

    // update
}
