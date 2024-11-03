/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com._dakl.project.services;

import com._dakl.project.model.Payment;
import java.util.List;

/**
 *
 * @author daklp
 */
public interface PaymentService {
    List<Payment> getAll();
    Boolean create(Payment payment);
    Payment findById(Integer paymentId);
    Boolean update(Payment cart);
    Boolean detete(Integer paymentId);
}
