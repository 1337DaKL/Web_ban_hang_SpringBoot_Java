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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author daklp
 */
@Entity
@Table(name ="payment")
public class Payment {
    @Id
    @Column(name="paymentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    @Column(name = "address")
    private String address;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "cartId")
    private Integer cartId;
    public Payment() {
    }

    public Payment(Integer paymentId, String address, String name, String phone, String email, Integer cartId) {
        this.paymentId = paymentId;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.cartId = cartId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    
    
}
