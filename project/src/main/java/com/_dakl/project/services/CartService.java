/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com._dakl.project.services;

import com._dakl.project.model.Cart;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author luong
 */

public interface CartService {
    List<Cart> getAll();
    Boolean create(Cart cart);
    Cart findById(Integer cartId);
    Boolean update(Cart cart);
    Boolean detete(Integer cartId);
}
