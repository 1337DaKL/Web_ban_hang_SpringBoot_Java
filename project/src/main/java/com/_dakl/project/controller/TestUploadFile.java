/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.controller;

import com._dakl.project.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author luong
 */
@Controller
@RequestMapping("/upload-file")
public class TestUploadFile {
    @Autowired
    
    private StorageService storageService;
    @GetMapping
    public String uploadDemo()
    {
        return "test-upload";
    }
    @PostMapping
    public String save(@RequestParam("file") MultipartFile file)
    {
        this.storageService.store(file);
        return "test-upload";
    }
}
