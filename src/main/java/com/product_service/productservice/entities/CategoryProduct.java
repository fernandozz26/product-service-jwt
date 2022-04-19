package com.product_service.productservice.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Table(name ="category_product")
@Entity
@Data
public class CategoryProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long categoryProductId;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    
}
