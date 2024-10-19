/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author luong
 */
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "productId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(name = "productName")
    private String productName;
    @Column(name = "price")
    private Double price;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "categoryId",referencedColumnName = "id")
    private Category category;

    public Product() {
    }

    public Product(Integer productId, String productName, Double price, String image, String description, Boolean status, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.description = description;
        this.status = status;
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getStatus() {
        return status;
    }

    public Category getCategory() {
        return category;
    }
    
}
