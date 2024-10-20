package com._dakl.project.controller.admin;

import com._dakl.project.model.Category;
import com._dakl.project.model.Product;
import com._dakl.project.services.CategoryService;
import com._dakl.project.services.ProductService;
import com._dakl.project.services.StorageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private StorageService  storageService;
    
    @Autowired
    private ProductService productService;
    @RequestMapping("/products")
    public String index(Model model)
    {
        List<Product> listProducts = this.productService.getAll();
        model.addAttribute("listProducts" , listProducts);
        return "admin/pages/products";
    }
    
    @RequestMapping("/product-add")
    public String add(Model model)
    {
        Product product = new Product();
        model.addAttribute("product" , product);
        List<Category> listCategory = this.categoryService.getAll();
        model.addAttribute("listCategory" , listCategory);
        product.setStatus(true);

        return "admin/pages/addProduct";
    }
    @PostMapping("add-product")
    public String addProduct(@ModelAttribute("product") Product product , @RequestParam("fileImage") MultipartFile file)
    {
        this.storageService.store(file);
        String fileName = file.getOriginalFilename(); 
        product.setImage(fileName);
        if(this.productService.create(product))
        {
            return "redirect:/admin/products";
        }
        return "admin/product-add";
    }
}
