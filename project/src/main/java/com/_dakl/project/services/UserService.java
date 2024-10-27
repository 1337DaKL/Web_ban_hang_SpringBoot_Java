/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com._dakl.project.services;

import com._dakl.project.model.User;
import java.util.Optional;

/**
 *
 * @author luong
 */
public interface UserService {
    User findByUserName(String userName);
    boolean checkLogin(String userName, String passWord);
    boolean registerUser(String username, String password, String email);
}
