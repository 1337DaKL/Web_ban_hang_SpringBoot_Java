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
import org.springframework.security.config.annotation.web.LogoutDsl;

/**
 *
 * @author luong
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf(csrf -> csrf.disable())  // Vô hiệu hóa CSRF
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/*").permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")  // Yêu cầu quyền ADMIN cho các trang /admin/**
                .anyRequest().authenticated()  // Các yêu cầu khác đều được phép
            )
            .formLogin(login -> login
                .loginPage("/logon")  // Trang login tùy chỉnh
                .loginProcessingUrl("/logon")  // URL xử lý login
                .usernameParameter("username")  // Tham số cho tên đăng nhập
                .passwordParameter("password")  // Tham số cho mật khẩu
                .defaultSuccessUrl("/admin", true)
                // Chuyển hướng sau khi login thành công
            ).logout(logout -> logout.logoutUrl("/admin-logout").logoutSuccessUrl("/logon"));
    
        return httpSecurity.build();
    }
    
    
    @Bean
    WebSecurityCustomizer webSecurityCustomizer(){
        return (web ) -> web.debug(true).ignoring().requestMatchers("/static/**" , "/client/**" , "/assets/**", "uploads/**");
    }
    
    
}
