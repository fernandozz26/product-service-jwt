package com.product_service.productservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AddToFavoriteModel {
    private Long productId;
    private Long userId;
}
