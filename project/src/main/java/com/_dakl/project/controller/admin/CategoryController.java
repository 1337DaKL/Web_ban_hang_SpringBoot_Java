/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com._dakl.project.model.Category;
import com._dakl.project.services.CategoryService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author luong
 */
@Controller

@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category")
    public String index(Model model)
    {
        List<Category> list = this.categoryService.getAll();
        model.addAttribute("list" , list);
        return "admin/pages/category";
    }
    @RequestMapping("/add-category")
    public String add(Model model)
    {
        Category category = new Category();
        category.setCategoryStatus(true);
        model.addAttribute("category" , category);
        
        return "admin/pages/category-add";
    }
    @PostMapping("/category-new")
        public String addCategory(@ModelAttribute("category") Category category) {
    // Xử lý logic thêm loại sản phẩm
        if(this.categoryService.create(category))
        {
            return "redirect:/admin/category";
        }
        else
        {
            return "admin/category/add-category";
        }
        
        }
    @GetMapping("/edit-category/{id}")
    public String edit(Model model , @PathVariable("id") Integer id)
    {
        Category category = this.categoryService.findById(id);
        model.addAttribute("category" , category);
        return "admin/pages/edit-category";
    }
    @PostMapping("/edit-category")
    public String update(@ModelAttribute("category") Category category) {
    // Xử lý logic thêm loại sản phẩm
        if(this.categoryService.create(category))
        {
            return "redirect:/admin/category";
        }
        else
        {
            return "admin/category/add-category";
        }
        
        }
    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable("id") Integer id)
    {
        if(this.categoryService.detete(id))
        {
            return "redirect:/admin/category";
            
        }
        else
        {
            return "redirect:/admin/category";
        }
    }
}
