/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.controller.client;

import com._dakl.project.model.Category;
import com._dakl.project.model.Product;
import com._dakl.project.services.CategoryService;
import com._dakl.project.services.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author luong
 */
@Controller
@RequestMapping("")
public class ClientController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("")
    public String home(Model model)
    {
        List<Category> listCategorys = this.categoryService.getAll();
        List<Category> categorys = new ArrayList<>();
        for(Category x : listCategorys)
        {
            if(x.getCategoryStatus() == true)
            {
                categorys.add(x);
            }
        }
        model.addAttribute("category" , categorys);
        return "client/pages/home";
    }
    @RequestMapping("/products")
    public String product(Model model)
    {
        List<Product> list = this.productService.getAll();
        List<Product> listProducts = new ArrayList<>();
        for(Product x : list)
        {
            if(x.getStatus() == true)
            {
                listProducts.add(x);
            }
        }
        model.addAttribute("product" , listProducts);
        List<Category> listCategorys = this.categoryService.getAll();
        List<Category> categorys = new ArrayList<>();
        for(Category x : listCategorys)
        {
            if(x.getCategoryStatus() == true)
            {
                categorys.add(x);
            }
        }
        model.addAttribute("category" , categorys);
        return "client/pages/product";
    }
    @RequestMapping("login")
    public String login()
    {
        return "client/pages/login";
    }
}
