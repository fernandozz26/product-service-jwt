package com.product_service.productservice.services.Product;

import java.util.List;

import com.product_service.productservice.entities.Category;
import com.product_service.productservice.entities.CategoryProduct;
import com.product_service.productservice.entities.Favorite;
import com.product_service.productservice.entities.Product;
import com.product_service.productservice.entities.ProductCart;

public interface ProductService {

    // Get Products
    public List<Product> mostPopularProducts();
    public List<Product> getAllProduct();
    public Product getProductById(Long productId);
    public List<ProductCart> getCartProducts(Long userId);
    public List<Favorite> getFavoriteProducts(Long userId);
    public List<CategoryProduct> getProductsByCategory(Long categoryId);
    public List<Product> getProductsBySale(Long saleId);
    //get categories
    public List<Category> getAllCategories();
    public Category getCategoryById(Long categoryId);
    
    //Save Products
    public void saveProduct(Product product);
    public void addProductToFavorite(Long productId, Long userId);
    public void addProductToCar(Product product, int quantity, Long userId);

    //delete products

    public void deleteProduct(Long productId);
    public void deleteProductFavorite(Long productId, Long userId);
    public void deleteProductCart(Long productId, Long userId);

    // update products
    public void updateProduct(Product product);
    public void updateCartProduct(Product product, int quantity, Long userId);

}
