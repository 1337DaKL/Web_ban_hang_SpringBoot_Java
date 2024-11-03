/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.services;

import com._dakl.project.model.Payment;
import com._dakl.project.repository.PaymentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daklp
 */
@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Boolean create(Payment payment) {
        try {
            paymentRepository.save(payment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Payment findById(Integer paymentId) {
        return paymentRepository.findById(paymentId).get();
    }

    @Override
    public Boolean update(Payment cart) {
        if(paymentRepository.existsById(cart.getPaymentId()))
        {
            paymentRepository.save(cart);
            return true;
        }
        return false;
    }

    @Override
    public Boolean detete(Integer paymentId) {
        try{
            this.paymentRepository.delete(findById(paymentId));
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
}
