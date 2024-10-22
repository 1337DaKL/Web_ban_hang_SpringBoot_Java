/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com._dakl.project.model.User;
import com._dakl.project.model.Category;
import com._dakl.project.model.Product;
import com._dakl.project.services.CategoryService;
import com._dakl.project.services.ProductService;
import com._dakl.project.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private UserService userService;
    @Autowired
    private User _userBean ;
    
    
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
    @RequestMapping("login-client")
    public String login()
    {
        return "client/pages/login";
    }
    
    @RequestMapping("/cart")
    public String cart()
    {
        return  "client/pages/cart";
    }
    @PostMapping("/checklogin")
    public String checkLogin(@RequestParam("username") String userName , @RequestParam("password") String passWord , Model model , HttpSession session)
    {
        if(userService.checkLogin(userName, passWord))
        {
            System.err.println("thành công");
            session.setAttribute("USERNAME",userName);
            return "client/pages/home";
        }
        else
        {
            System.out.println("Sai");
            model.addAttribute("error" , "Tài khoản hoặc mật khẩu không chính xác");
        }
        return "client/pages/login";
    }
    
}
