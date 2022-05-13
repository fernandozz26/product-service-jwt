package com.product_service.productservice.services.Product;

import java.util.List;

import javax.transaction.Transactional;

import com.product_service.productservice.entities.*;
import com.product_service.productservice.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductServiceImp implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductCartRepository productCartRepository;

    @Autowired 
    FavoriteRepository favoriteRepository;

    @Autowired
    CategoryProductRepository categoryProductRepository;

    @Autowired
    ImageProductRepository imageProductRepository;

    @Override
    public List<Product> mostPopularProducts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Product> getAllProduct(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product getProductById(Long productId) {
        Product product = productRepository.getById(productId);
        return product;
    }

    @Override
    public List<ProductCart> getCartProducts(Long userId) {
        Cart cart= cartRepository.getByUserId(userId);
        List<ProductCart> productCarts = productCartRepository.getByCartId(cart.getCartId());
        return productCarts;
    }

    @Override
    public List<Favorite> getFavoriteProducts(Long userId) {
        List<Favorite> favorites = favoriteRepository.getByUserId(userId);
        return favorites;
    }

    @Override
    public List<CategoryProduct> getProductsByCategory(Long categoryId) {
        List<CategoryProduct> categoryProducts = categoryProductRepository.getByCategoryId(categoryId);
        return categoryProducts;
    }

    @Override
    public List<Product> getProductsBySale(Long saleId) {
        // TODO Auto-generated method stub
        return null;
    }    
    @Override
    public List<Category> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
    @Override
    public Category getCategoryById(Long categoryId){
        Category category = categoryRepository.getById(categoryId);
        return category;
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
        Product lastProduct = productRepository.getLastProduct();
        ImageProduct imageProduct = ImageProduct.builder()
        .productId(lastProduct.getProductId().intValue())
        .imageProductId(Long.valueOf(0)).productImgUrl("")
        .build();

        // up to 8 images can save
        imageProductRepository.save(imageProduct);
        imageProductRepository.save(imageProduct);
        imageProductRepository.save(imageProduct);
        imageProductRepository.save(imageProduct);
        imageProductRepository.save(imageProduct);
        imageProductRepository.save(imageProduct);
        imageProductRepository.save(imageProduct);
        imageProductRepository.save(imageProduct);
        
    }

    @Override
    public void addProductToFavorite(Long productId, Long userId) {
        Long favoriteId = 0L;
        Product product = productRepository.getById(productId);
        Favorite favorite = Favorite.builder().favoriteId(favoriteId).product(product).userId(userId).build();
        favoriteRepository.save(favorite);
        
    }

    @Override
    public void addProductToCar(Product product, int quantity, Long userId) {
        Cart cart = cartRepository.getByUserId(userId);
        ProductCart productCart = ProductCart.builder().cartId(0L).product(product).cartId(cart.getCartId()).quantity(quantity).build();
        productCartRepository.save(productCart);
    }
    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void deleteProductFavorite(Long favoriteId) {
        favoriteRepository.deleteFavoriteById(favoriteId);
    }
    @Override
    public void deleteProductCart(Long productId, Long cartId) {
        ProductCart productCart = productCartRepository.getByUserIdAndProductId(cartId, productId);
        productCartRepository.delete(productCart);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
        
    }

    @Override
    public void updateCartProduct(Long productCartId, int quantity) {
        
        productCartRepository.updateQuantityProductCart(quantity, productCartId);
    }

    @Override
    public List<Product> getProductsBySearch(String word) {
        List<Product> products = productRepository.getProductsBySearch(word);
        return products;
    }

    @Override
    public List<Product> getProductsBySearchId(Long id) {
        List<Product> products = productRepository.getProductsBySearchId(id);
        return products;
    }

    

    @Override
    public boolean existFavoriteProduct(Long userId, Long productId) {
        if(favoriteRepository.existsFavoriteByProductId(userId, productId) == 1){
            return true;
        }

        return false;
    }

    @Override
    public boolean existCartProduct(Long userId, Long productId) {
        if(cartRepository.existsCartByProductId(userId, productId) == 1){
            return true;
        }
        return false;
    }

    @Override
    public ProductCart getProductCartByCartId(Long cartId, Long productId) {
        ProductCart productCart = productCartRepository.getByCartId(cartId, productId);
        return productCart;
    }

    @Override
    public void addProductToCar(ProductCart productCart) {
        productCartRepository.save(productCart);
    }

    
  
}
