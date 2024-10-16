package com._dakl.project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    @RequestMapping("admin/products")
    public String index()
    {
        return "admin/pages/products";
    }
}
