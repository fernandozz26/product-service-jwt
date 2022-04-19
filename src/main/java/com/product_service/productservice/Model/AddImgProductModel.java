package com.product_service.productservice.Model;

import java.util.ArrayList;

import com.product_service.productservice.entities.ImageProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class AddImgProductModel {
    private ArrayList<ImageProduct> imageProducts;
}
