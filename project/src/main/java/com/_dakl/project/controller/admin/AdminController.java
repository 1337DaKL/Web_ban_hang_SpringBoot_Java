/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author luong
 */
@Controller
@RequestMapping("/admin")

public class AdminController {
    @GetMapping 
        public String index(){
        return "redirect:/admin/";
    }
    @RequestMapping("/")
    public String admin()
    {
        return "admin/pages/index";
    }
}
