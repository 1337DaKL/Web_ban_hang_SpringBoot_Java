/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com._dakl.project.services.CustomUserDetailService;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author luong
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    @Lazy
    private CustomUserDetailService customUserDetailService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/*").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/delete-cart-item/**").permitAll()
                        .requestMatchers("/add-to-cart/**").permitAll()
                        .requestMatchers("/cart/edit-cart-item/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/logon")
                        .loginProcessingUrl("/logon")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/admin", true))
                .logout(logout -> logout.logoutUrl("/admin-logout").logoutSuccessUrl("/logon"))
                .exceptionHandling(exception -> exception.accessDeniedPage("/403"));;
        return httpSecurity.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(true).ignoring().requestMatchers("/static/**", "/client/**", "/assets/**",
                "uploads/**");
    }

}
