/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.services;

import com._dakl.project.model.User;
import com._dakl.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author luong
 */
@Service
public class UserSeviceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // BCryptPasswordEncoder để mã hóa và so sánh mật khẩu

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean checkLogin(String userName, String passWord) {
        // Tìm kiếm người dùng dựa trên userName
        User user = findByUserName(userName);

        // Kiểm tra xem người dùng có tồn tại không
        if (user != null) {
            // So sánh mật khẩu đã nhập (không mã hóa) với mật khẩu đã mã hóa trong DB
            if (passwordEncoder.matches(passWord, user.getPassWord())) {
                return true; // Mật khẩu đúng
            }
        }

        return false; // Mật khẩu sai hoặc không tồn tại tài khoản
    }
}
