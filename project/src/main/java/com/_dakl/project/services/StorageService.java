/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com._dakl.project.services;

/**
 *
 * @author luong
 */
import org.springframework.web.multipart.MultipartFile;
public interface StorageService {
    void  init();
    void store(MultipartFile file);
}
