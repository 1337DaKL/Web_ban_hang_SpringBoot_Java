/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com._dakl.project.services;


import com._dakl.project.model.Product;
import java.util.List;

/**
 *
 * @author luong
 */
public interface ProductService {
    List<Product> getAll();
    Boolean create(Product product);
    Product findById(Integer productId);
    Boolean update(Product product);
    Boolean detete(Integer productId);
}

