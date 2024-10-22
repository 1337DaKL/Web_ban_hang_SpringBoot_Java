/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.services;

import com._dakl.project.model.Product;
import com._dakl.project.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author luong
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Boolean create(Product product) {
        try {
            this.productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product findById(Integer productId) {
        return this.productRepository.findById(productId).get();
    }

    @Override
    public Boolean update(Product product) {
         try {
            this.productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean detete(Integer productId) {
        try {
            this.productRepository.delete(findById(productId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
