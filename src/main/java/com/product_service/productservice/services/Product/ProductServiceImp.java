package com.product_service.productservice.services.Product;

import java.util.List;

import javax.transaction.Transactional;

import com.product_service.productservice.entities.*;
import com.product_service.productservice.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

    @Transactional
    @Override
    public List<Product> getAllProduct(){
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Transactional
    @Override
    public Product getProductById(Long productId) {
        Product product = productRepository.getById(productId);
        return product;
    }

    @Transactional
    @Override
    public List<ProductCart> getCartProducts(Long userId) {
        Cart cart= cartRepository.getByUserId(userId);
        List<ProductCart> productCarts = productCartRepository.getByCartId(cart.getCartId());
        return productCarts;
    }
    @Transactional
    @Override
    public List<Favorite> getFavoriteProducts(Long userId) {
        List<Favorite> favorites = favoriteRepository.getByUserId(userId);
        return favorites;
    }

    @Transactional
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
    @Transactional    
    @Override
    public List<Category> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
    @Transactional
    @Override
    public Category getCategoryById(Long categoryId){
        Category category = categoryRepository.getById(categoryId);
        return category;
    }

    @Override
    @Transactional
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

    @Transactional
    @Override
    public void addProductToFavorite(Long productId, Long userId) {
        Long favoriteId = 0L;
        Product product = productRepository.getById(productId);
        Favorite favorite = Favorite.builder().favoriteId(favoriteId).product(product).userId(userId).build();
        favoriteRepository.save(favorite);
        
    }

    @Transactional
    @Override
    public void addProductToCar(Product product, int quantity, Long userId) {
        Cart cart = cartRepository.getByUserId(userId);
        ProductCart productCart = ProductCart.builder().cartId(0L).product(product).cartId(cart.getCartId()).quantity(quantity).build();
        productCartRepository.save(productCart);
    }
    @Transactional
    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Transactional
    @Override
    public void deleteProductFavorite(Long productId, Long userId) {
        Favorite favorite = favoriteRepository.getByUserIdAndProductId(userId, productId);
        favoriteRepository.delete(favorite);
        
    }

    @Transactional
    @Override
    public void deleteProductCart(Long productId, Long cartId) {
        ProductCart productCart = productCartRepository.getByUserIdAndProductId(cartId, productId);
        productCartRepository.delete(productCart);
    }

    @Transactional
    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
        
    }
    @Transactional
    @Override
    public void updateCartProduct(Product product, int quantity, Long cartId) {
        ProductCart productCart = productCartRepository.getByUserIdAndProductId(cartId, product.getProductId());
        ProductCart newProductCart = ProductCart.builder().cartId(cartId).product(product).productCartId(productCart.getCartId()).quantity(quantity).build();
        productCartRepository.save(newProductCart);
    }
  
}
