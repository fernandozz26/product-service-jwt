package com.product_service.productservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartModel {
    
    private Long productId;
    private int quantity;
    private String username;
}
