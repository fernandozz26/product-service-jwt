package com.product_service.productservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
@Data
@Entity
@Table(name = "sale")
public class Sale implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sale_id", nullable = false)
    private Long saleId;

    @Column
    private Long userId;

    @Column
    private double total;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;
}
