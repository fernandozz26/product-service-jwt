package com.product_service.productservice.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product_service.productservice.entities.Cart;
import com.product_service.productservice.entities.Favorite;
import com.product_service.productservice.entities.ImageProduct;
import com.product_service.productservice.entities.MessageError;
import com.product_service.productservice.entities.Product;
import com.product_service.productservice.entities.ProductCart;
import com.product_service.productservice.entities.User;
import com.product_service.productservice.repositories.ProductCartRepository;
import com.product_service.productservice.security.service.UserService;
import com.product_service.productservice.services.ImgProduct.ImageProductServiceImp;
import com.product_service.productservice.services.Product.ProductServiceImp;
import com.product_service.productservice.services.cart.CartServiceImp;
import com.product_service.productservice.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    ProductServiceImp productService;

    @Autowired
    ImageProductServiceImp imageProductService;

    @Autowired
    UserService userService;

    @Autowired
    CartServiceImp cartService;

    // Getting data
    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProduct();
        if(products.equals(null) || products == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(products);
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
        Product product = productService.getProductById(productId);
        if(product.equals(null) || product == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(product);
        }
    }

    @GetMapping("/product/search/{word}")
    public ResponseEntity<List<Product>> getProductLike(@PathVariable("word") String word){

        Long id;
        List<Product> products;
        try{
            id = Long.parseLong(word);
            products = productService.getProductsBySearchId(id);
        }catch(Exception ex){
            products = productService.getProductsBySearch(word);
        }

        if(products.equals(null) || products == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(products);
        }
    }

    @GetMapping("/product/cart/{username}")
    public ResponseEntity<List<ProductCart>> getCartProducts(@PathVariable("username") String username){
        try{
            User user = userService.findByUsername(username);
            List<ProductCart> productCarts = productService.getCartProducts(user.getUserId());
            if(productCarts.equals(null) || productCarts == null){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(productCarts);
            }
        }catch(Exception ex){
            System.out.println(ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/product/favorite/{username}")
    public ResponseEntity<List<Favorite>> getFavoriteProducts(@PathVariable("username") String username){
        
            User user = userService.findByUsername(username);
            
            List<Favorite> Favoriteroducts = productService.getFavoriteProducts(user.getUserId());
            
            if(Favoriteroducts.equals(null) || Favoriteroducts == null){
                return ResponseEntity.noContent().build();
            }
                
            return ResponseEntity.ok(Favoriteroducts);
    }

 
    // Save data
    @PreAuthorize("hasRole('ADMIN')") // solo el admin puede guardar productos
    @PostMapping( value = "/product", consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> saveProduct(@RequestBody Product product, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }else{
            try{
                productService.saveProduct(product);
                return ResponseEntity.ok("Added");
            }catch(Exception e){
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @PostMapping( value = "/product/cart", consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addProductToCar(@RequestBody AddToCartModel addToCartModel, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }else{
            try{
                User user = userService.findByUsername(addToCartModel.getUsername());
                Product product = productService.getProductById(addToCartModel.getProductId());
                
                // si el usuario no tiene un carrito
                if(!cartService.existCart(user.getUserId())){
                    Cart cart = new Cart(0L, user.getUserId());
                    cartService.saveNewCart(cart); 
                }

                if(addToCartModel.getQuantity() > product.getStock()){
                    return ResponseEntity.noContent().build();
                }
                // si no existe el producto en el carrito lo agrega
                if(!productService.existCartProduct(user.getUserId(), addToCartModel.getProductId())){
                    productService.addProductToCar(product, addToCartModel.getQuantity(), user.getUserId());
                }else{ // si existe lo actualiza
                    Cart cart = cartService.getCardByUserId(user.getUserId());
                    ProductCart productCart = productService.getProductCartByCartId(cart.getCartId(), addToCartModel.getProductId());
                    productCart.setQuantity(addToCartModel.getQuantity());
                    productService.addProductToCar(productCart);
                }
                return ResponseEntity.accepted().build();
            }catch(Exception e){
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @PostMapping( value = "/product/favorite", consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addProductToFavorite(@RequestBody AddToFavoriteModel addToFavoriteModel, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }else{
            try{
                User user = userService.findByUsername(addToFavoriteModel.getUsername());
                if(!productService.existFavoriteProduct(user.getUserId(), addToFavoriteModel.getProductId())){
                    productService.addProductToFavorite(addToFavoriteModel.getProductId(), user.getUserId());
                }else{
                    ResponseEntity.ok("Already exists");
                }
                return ResponseEntity.accepted().build();
            }catch(Exception e){
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    // Updating data

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(value = "/product/img")
    public ResponseEntity<String> addImagesToProduct(@RequestBody ArrayList<ImageProduct> imageProducts, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }else{
            try{
                
                imageProducts.forEach(img -> {
                    System.out.println(img);
                    imageProductService.saveImageProduct(img);
                });
                return ResponseEntity.ok("Updated");
            }catch(Exception e){
                return ResponseEntity.internalServerError().build();
            }
        }
    }
    
    @PreAuthorize("hasRole('ADMIN')") // solo el admin puede guardar productos
    @PatchMapping( value = "/product", consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateProduct(@RequestBody Product product, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }else{
            try{
                productService.saveProduct(product);
                return ResponseEntity.ok("Updated");
            }catch(Exception e){
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @PatchMapping( value = "/product/cart", consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> updateCart(@RequestBody UpdateProductCartModel cartModel, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }else{
            try{
                productService.updateCartProduct(cartModel.getProductCartId(), cartModel.getQuantity());
                return ResponseEntity.accepted().build();
            }catch(Exception e){
                return ResponseEntity.internalServerError().build();
            }
        }
    }
    
    // Deleting data
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        
        try{
            productService.deleteProduct(id);
            return ResponseEntity.ok("Deleted");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("/product/favorite/{id}")
    public ResponseEntity<String> deleteFavoriteProduct(@PathVariable("id") Long id){
        try{
            productService.deleteProductFavorite(id);
            return ResponseEntity.accepted().build();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @DeleteMapping("/product/cart/{id}")
    public ResponseEntity<String> deleteCatProduct(@PathVariable("id") Long id){
        try{
            productService.deleteProductFavorite(id);
            return ResponseEntity.accepted().build();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    // Msg Format

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
        .map(err -> {
            Map<String, String> error = new HashMap<>();
            error.put(err.getField(), err.getDefaultMessage());
            return error;
        }).collect(Collectors.toList());

        MessageError errorMesage = MessageError.builder().code("01").messages(errors).build();
        
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try{
            json = mapper.writeValueAsString(errorMesage);
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return json;
    }
}
