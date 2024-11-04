/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.controller.admin;

import com._dakl.project.model.Cart;
import com._dakl.project.model.CartItem;
import com._dakl.project.model.Payment;
import com._dakl.project.repository.PaymentRepository;
import com._dakl.project.services.CartService;
import com._dakl.project.services.PaymentService;
import java.util.List;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author daklp
 */
@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CartService cartService;
    @GetMapping("/order")
public String getOrder(Model model) {
    List<Payment> listPayment = paymentService.getAll();
    TreeMap<Integer, Cart> cart = new TreeMap<>();
    TreeMap<Integer , Long> total = new TreeMap<>();
    for (Payment x : listPayment) {
        Integer cartId = x.getCartId();
        
        // Kiểm tra cartId có phải là null hay không
        if (cartId != null) {
        Cart foundCart = cartService.findById(cartId);
        if (foundCart != null) {
            long tong = 0;
            for (CartItem cartItem : foundCart.getCartItems()) {
                tong += (cartItem.getQuantity() * cartItem.getProduct().getPrice());
                total.put(cartId, tong);
            }
            cart.put(cartId, foundCart);
        } else {
            // Handle the case where no cart was found with cartId
            System.out.println("No cart found for id: " + cartId);
        }
    }

    }
    model.addAttribute("total" , total);
    model.addAttribute("cart", cart);
    model.addAttribute("listPayment", listPayment);
    return "admin/pages/order";
}
    @GetMapping("/delete-order/{paymentId}")
    public String deleteOrder(@PathVariable("paymentId") Integer paymentId)
    {
        if(paymentService.detete(paymentId))
        {
            return "redirect:/admin/order";
        }
        else
        {
            return "redirect:/admin/order";
        }
    }
}
