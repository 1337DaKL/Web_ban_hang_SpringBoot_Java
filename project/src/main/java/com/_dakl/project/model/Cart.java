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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

/**
 *
 * @author luong
 */
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 
    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems;

    public Cart() {
    }

    public Cart(Integer id, Set<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }

    public Integer getId() {
        return id;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
    
}
