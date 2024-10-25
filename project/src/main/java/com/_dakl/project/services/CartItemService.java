/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com._dakl.project.services;

import com._dakl.project.model.CartItem;
import java.util.List;

/**
 *
 * @author daklp
 */
public interface CartItemService {
    List<CartItem> getAll();
    Boolean create(CartItem cartItem);
    CartItem findById(Integer cartItemId);
    Boolean update(CartItem cart);
    Boolean detete(Integer cartItemId);
}
