package com._dakl.project.controller.admin;

import com._dakl.project.model.Category;
import com._dakl.project.model.Product;
import com._dakl.project.services.CategoryService;
import com._dakl.project.services.ProductService;
import com._dakl.project.services.StorageService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String index(Model model , @RequestParam(name ="pageNo" , defaultValue = "1") Integer pageNo)
    {
        Page<Product> list = this.productService.getAll(pageNo);
        model.addAttribute("listProducts" , list);
        model.addAttribute("totalPage" , list.getTotalPages());
        model.addAttribute("currentPage" , pageNo);
        return "admin/pages/products";
    }
    
    @RequestMapping("/product-add")
    public String add(Model model)
    {
        Product product = new Product();
        model.addAttribute("product" , product);
        List<Category> listCategory = this.categoryService.getAll();
        List<Category> listCate = new ArrayList<>();
        for(Category x : listCategory)
        {
            if(x.getCategoryStatus() == true)
            {
                listCate.add(x);
            }
        }
        model.addAttribute("listCategory" , listCate);
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
    @GetMapping("/delete-product/{productId}")
    public String deleteProduct(@PathVariable("productId") Integer productId)
    {
        if(productService.detete(productId))
        {
            return "redirect:/admin/products";
        }
        else
        {
            return "redirect:/admin/products";
        }
    }
    @GetMapping("/edit-product/{productId}")
    public String edit(Model model , @PathVariable("productId") Integer productId)
    {
        List<Category> category= this.categoryService.getAll();
        Product product = this.productService.findById(productId);
        model.addAttribute("product" , product);
        model.addAttribute("category" , category);
        return "admin/pages/edit-product";
    }
    @PostMapping("/edit-product")
    public String update(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file) {
    // Xử lý logic thêm loại sản phẩm
        if(!file.isEmpty())
        {
            this.storageService.store(file);
            String fileName = file.getOriginalFilename(); 
            product.setImage(fileName);
        }
        else
        {
            Product existingProduct = productService.findById(product.getProductId());
            product.setImage(existingProduct.getImage());
        }
        if(this.productService.update(product))
            {
            return "redirect:/admin/products";
            }
            else
            {
                return "admin/category/add-product";
            }
    }
}
