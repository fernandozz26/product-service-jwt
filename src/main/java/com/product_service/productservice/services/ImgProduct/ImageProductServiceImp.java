package com.product_service.productservice.services.ImgProduct;

import com.product_service.productservice.entities.ImageProduct;
import com.product_service.productservice.repositories.ImageProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageProductServiceImp  implements ImageProductService{

    @Autowired
    ImageProductRepository imageProductRepository;

    @Override
    public void saveImageProduct(ImageProduct imageProduct) {
        imageProductRepository.save(imageProduct);
    }
    
}
