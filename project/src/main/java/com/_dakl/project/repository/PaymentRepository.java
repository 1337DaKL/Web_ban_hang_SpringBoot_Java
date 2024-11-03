/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com._dakl.project.repository;

import com._dakl.project.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author daklp
 */
public interface PaymentRepository  extends JpaRepository<Payment,Integer >{
    
}
