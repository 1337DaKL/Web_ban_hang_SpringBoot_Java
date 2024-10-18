package com._dakl.project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @RequestMapping("/products")
    public String index()
    {
        return "admin/pages/products";
    }
    
    @RequestMapping("/product-add")
    public String add()
    {
        return "admin/pages/add";
    }
}
