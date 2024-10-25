/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.services;

import com._dakl.project.model.CartItem;
import com._dakl.project.repository.CartItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daklp
 */
@Service
public class CartItemServiceImpl implements CartItemService{
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public Boolean create(CartItem cartItem) {
        try {
            cartItemRepository.save(cartItem);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CartItem findById(Integer cartItemId) {
        return cartItemRepository.findById(cartItemId).get();
    }

    @Override
    public Boolean update(CartItem cart) {
        if (cartItemRepository.existsById(cart.getId())) {
            cartItemRepository.save(cart);
            return true;
        }
        return false;
    }

    @Override
    public Boolean detete(Integer cartItemId) {
        try {
            this.cartItemRepository.delete(findById(cartItemId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
