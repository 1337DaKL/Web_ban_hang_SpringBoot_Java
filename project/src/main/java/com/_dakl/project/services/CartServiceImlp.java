/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.services;

import com._dakl.project.model.Cart;
import com._dakl.project.repository.CartRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author luong
 */

@Service
public class CartServiceImlp implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    public Boolean create(Cart cart) {
        try {
            cartRepository.save(cart);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Cart findById(Integer cartId) {
        return cartRepository.findById(cartId).get();
    }

    @Override
    public Boolean update(Cart cart) {
        if (cartRepository.existsById(cart.getId())) {
            cartRepository.save(cart);
            return true;
        }
        return false;
    }
    @Override
    public Boolean detete(Integer cartId) {
        try {
            this.cartRepository.delete(findById(cartId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
}
