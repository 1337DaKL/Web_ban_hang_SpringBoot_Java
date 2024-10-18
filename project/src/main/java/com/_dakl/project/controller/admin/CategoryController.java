/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.controller.admin;

import com._dakl.project.model.Category;
import com._dakl.project.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author luong
 */
@Controller

@RequestMapping("/admin")
public class CategoryController {
    @GetMapping("/category")
    public String index()
    {
        return "admin/pages/category";
    }
    @RequestMapping("/add-category")
    public String add()
    {
        return "admin/pages/category-add";
    }
}
