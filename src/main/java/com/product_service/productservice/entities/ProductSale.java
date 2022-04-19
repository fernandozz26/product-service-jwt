package com.product_service.productservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "product_sale")
@Entity
@Data
public class ProductSale implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_sale_id", nullable = false)
    private Long productSaleId;

    @JoinColumn(name = "product_id")
    @OneToOne(fetch=FetchType.LAZY)
    private Product product;

    @JoinColumn(name = "sale_id")
    @OneToOne(fetch=FetchType.LAZY)
    private Sale sale;

    @Column
    private double subtotal;

    @Column
    private int quantity;
}
