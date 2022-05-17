package com.product_service.productservice.repositories;

import java.util.List;

import com.product_service.productservice.entities.ProductCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart,Long> {
    @Query(nativeQuery = true, value = "select * from product_cart where cart_id = :cartId and product_status = 'ADDED'")
    public List<ProductCart> getByCartId(@Param(value= "cartId") Long cartId);

    @Query(nativeQuery = true, value = "select * from product_cart where cart_id = :cartId and product_id =:productId")
    public ProductCart getByCartId(@Param(value= "cartId") Long cartId, @Param(value= "productId") Long productId);

    @Query(nativeQuery = true, value = "select * from product_cart where cart_id = :cartId and product_id = :productId")
    public ProductCart getByUserIdAndProductId(@Param( value = "cartId") Long cartId, @Param("productId") Long productId);

    @Modifying
    @Query(nativeQuery = true, value = "update product_cart set quantity = :quantity where product_cart_id = :id")
    public void  updateQuantityProductCart(@Param("quantity") int quantity, @Param("id") Long productCartId);

    @Modifying
    @Procedure(procedureName = "PAY_CART")
    public void payCart(int cartId);

    /**
     * 
     * @Query(value = "{call(PAY_CART(:cartId))}", nativeQuery = true)
     * public void payCart(@Param("cartId") int cartId);
     */

}
