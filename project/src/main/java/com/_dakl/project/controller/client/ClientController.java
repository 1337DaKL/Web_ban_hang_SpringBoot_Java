/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com._dakl.project.controller.client;

import com._dakl.project.model.Cart;
import com._dakl.project.model.CartItem;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com._dakl.project.model.User;
import com._dakl.project.model.Category;
import com._dakl.project.model.Product;
import com._dakl.project.services.CartItemService;
import com._dakl.project.services.CartService;
import com._dakl.project.services.CategoryService;
import com._dakl.project.services.ProductService;
import com._dakl.project.services.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;
    
    @RequestMapping("")
    public String home(Model model , HttpSession session)
    {
        Cart cart = (Cart) session.getAttribute("CART");
        System.out.println(cart);
        if (cart != null) {
            Set<CartItem> listItem = cart.getCartItems();
            System.out.println(listItem);
            model.addAttribute("listItem", listItem);
            long total = 0;
            for(CartItem x : listItem)
            {
                total += (x.getQuantity() * x.getProduct().getPrice());
            }
            model.addAttribute("total" , total);
            model.addAttribute("size" , listItem.size());
        } else {
            // Nếu giỏ hàng trống, truyền thông báo
            model.addAttribute("listItem", new ArrayList<>());
            model.addAttribute("message", "Giỏ hàng của bạn trống");    
        }
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
    public String product(Model model , HttpSession session)
    {
        Cart cart = (Cart) session.getAttribute("CART");
        System.out.println(cart);
        if (cart != null) {
            Set<CartItem> listItem = cart.getCartItems();
            System.out.println(listItem);
            model.addAttribute("listItem", listItem);
            long total = 0;
            for(CartItem x : listItem)
            {
                total += (x.getQuantity() * x.getProduct().getPrice());
            }
            model.addAttribute("total" , total);
            model.addAttribute("size" , listItem.size());
        } else {
            // Nếu giỏ hàng trống, truyền thông báo
            model.addAttribute("listItem", new ArrayList<>());
            model.addAttribute("message", "Giỏ hàng của bạn trống");    
        }
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
    @PostMapping("/checklogin")
    public String checkLogin(
        @RequestParam("username") String userName,
        @RequestParam("password") String passWord,
        Model model,
        HttpSession session) {

    // Kiểm tra tên đăng nhập và mật khẩu
    if (userService.checkLogin(userName, passWord)) {
        System.err.println("Đăng nhập thành công");

        // Lưu tên người dùng vào session
        session.setAttribute("USERNAME", userName);

        // Lấy thông tin người dùng từ cơ sở dữ liệu
        User user = userService.findByUserName(userName);

        // Nếu người dùng chưa có giỏ hàng, tạo mới và lưu vào DB
        if (user.getCart() == null) {
            Cart cart = new Cart();
            cart.setUser(user);
            cartService.create(cart);  // Lưu giỏ hàng mới vào DB
            user.setCart(cart);
        }

        // Lưu giỏ hàng vào session để sử dụng ở các phần khác

        Cart cart = user.getCart();
        System.out.println(cart.getCartItems());
        session.setAttribute("CART", cart); // Lưu giỏ hàng vào session


        // Chuyển hướng về trang chủ sau khi đăng nhập thành công
        return "redirect:/";  // Dùng redirect để tránh gửi lại form
    } else {
        System.out.println("Sai tài khoản hoặc mật khẩu");

        // Thêm thông báo lỗi vào model
        model.addAttribute("error", "Tài khoản hoặc mật khẩu không chính xác");

        // Quay trở lại trang login nếu đăng nhập không thành công
        return "client/pages/login";
    }
}
    @RequestMapping("/cart")
    public String cart(HttpSession session , Model model)
    {
        Cart cart = (Cart) session.getAttribute("CART");
        System.out.println(cart);
        if (cart != null) {
            Set<CartItem> listItem = cart.getCartItems();
            System.out.println(listItem);
            model.addAttribute("listItem", listItem);
            long total = 0;
            for(CartItem x : listItem)
            {
                total += (x.getQuantity() * x.getProduct().getPrice());
            }
            model.addAttribute("total" , total);
            model.addAttribute("size" , listItem.size());
        } else {
            // Nếu giỏ hàng trống, truyền thông báo
            model.addAttribute("listItem", new ArrayList<>());
            model.addAttribute("message", "Giỏ hàng của bạn trống");    
        }
        return  "client/pages/cart";
    }
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        // Lấy giỏ hàng từ session
        Cart cart = (Cart) session.getAttribute("CART");
        System.out.println(cart);
        if (cart != null) {
            Set<CartItem> listItem = cart.getCartItems();
            System.out.println(listItem);
            model.addAttribute("listItem", listItem);
            long total = 0;
            for(CartItem x : listItem)
            {
                total += (x.getQuantity() * x.getProduct().getPrice());
            }
            model.addAttribute("total" , total);
            model.addAttribute("size" , listItem.size());
        } else {
            // Nếu giỏ hàng trống, truyền thông báo
            model.addAttribute("listItem", new ArrayList<>());
            model.addAttribute("message", "Giỏ hàng của bạn trống");    
        }

        return "client/pages/cart";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Hủy session
        return "redirect:/login-client";  // Chuyển hướng về trang đăng nhập
    }
    @GetMapping("/delete-cart-item/{cartItemId}")
    public String deleteCartItem(@PathVariable("cartItemId") Integer cartItemId)
    {
        if(cartItemService.detete(cartItemId))
        {
            return "redirect:/products";
        }
        else
        {
            return "redirect:/products";
        }
    }
}
