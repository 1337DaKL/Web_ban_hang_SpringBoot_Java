/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author luong
 */
public class TestPassword {
    public static void main(String[] args) {
        System.out.println("");
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        System.out.println("");
    }
}
