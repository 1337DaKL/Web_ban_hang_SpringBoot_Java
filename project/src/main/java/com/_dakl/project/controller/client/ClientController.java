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
import com._dakl.project.model.Payment;
import com._dakl.project.model.Product;
import com._dakl.project.services.CartItemService;
import com._dakl.project.services.CartService;
import com._dakl.project.services.CategoryService;
import com._dakl.project.services.PaymentService;
import com._dakl.project.services.ProductService;
import com._dakl.project.services.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @Autowired
    private PaymentService paymentService;

    @RequestMapping("")
    public String home(Model model, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("CART");
        System.out.println(cart);
        if (cart != null) {
            Set<CartItem> listItem = cart.getCartItems();
            System.out.println(listItem);
            model.addAttribute("listItem", listItem);
            long total = 0;
            for (CartItem x : listItem) {
                total += (x.getQuantity() * x.getProduct().getPrice());
            }
            model.addAttribute("total", total);
            model.addAttribute("size", listItem.size());
        } else {
            // Nếu giỏ hàng trống, truyền thông báo
            model.addAttribute("listItem", new ArrayList<>());
            model.addAttribute("message", "Giỏ hàng của bạn trống");
        }
        List<Category> listCategorys = this.categoryService.getAll();
        List<Category> categorys = new ArrayList<>();
        for (Category x : listCategorys) {
            if (x.getCategoryStatus() == true) {
                categorys.add(x);
            }
        }
        List<Product> products = productService.getAll();
        int cnt = 0;
        Set<Product> demo = new HashSet<>();
        for (Product x : products) {
            cnt++;
            if (cnt <= 5)
                demo.add(x);
        }
        model.addAttribute("demo", demo);
        model.addAttribute("category", categorys);
        return "client/pages/home";
    }

    @RequestMapping("/products")
    public String product(Model model, HttpSession session,
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        Cart cart = (Cart) session.getAttribute("CART");
        if (cart != null) {
            Set<CartItem> listItem = cart.getCartItems();
            System.out.println(listItem);
            model.addAttribute("listItem", listItem);
            long total = 0;
            for (CartItem x : listItem) {
                total += (x.getQuantity() * x.getProduct().getPrice());
            }
            model.addAttribute("total", total);
            model.addAttribute("size", listItem.size());
        } else {
            // Nếu giỏ hàng trống, truyền thông báo
            model.addAttribute("listItem", new ArrayList<>());
            model.addAttribute("message", "Giỏ hàng của bạn trống");
        }
        Page<Product> list = this.productService.getAllClient(pageNo);
        List<Product> listProducts = new ArrayList<>();
        for (Product x : list) {
            if (x.getStatus() == true) {
                listProducts.add(x);
            }
        }
        model.addAttribute("product", listProducts);
        List<Category> listCategorys = this.categoryService.getAll();
        List<Category> categorys = new ArrayList<>();
        for (Category x : listCategorys) {
            if (x.getCategoryStatus() == true) {
                categorys.add(x);
            }
        }
        model.addAttribute("totalPage", list.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("category", categorys);
        return "client/pages/product";
    }

    @RequestMapping("login-client")
    public String login() {
        return "client/pages/login";
    }

    @PostMapping("/checklogin")
    public String checkLogin(
            @RequestParam("username") String userName,
            @RequestParam("password") String passWord,
            Model model,
            HttpSession session) {

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
                cartService.create(cart); // Lưu giỏ hàng mới vào DB
                user.setCart(cart);
            }

            // Lưu giỏ hàng vào session để sử dụng ở các phần khác

            Cart cart = user.getCart();
            System.out.println(cart.getCartItems());
            session.setAttribute("CART", cart); // Lưu giỏ hàng vào session

            // Chuyển hướng về trang chủ sau khi đăng nhập thành công
            return "redirect:/"; // Dùng redirect để tránh gửi lại form
        } else {
            System.out.println("Sai tài khoản hoặc mật khẩu");

            // Thêm thông báo lỗi vào model
            model.addAttribute("error", "Tài khoản hoặc mật khẩu không chính xác");

            // Quay trở lại trang login nếu đăng nhập không thành công
            return "client/pages/login";
        }
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        // Lấy giỏ hàng từ session
        Cart cart = (Cart) session.getAttribute("CART");

        if (cart == null) {
            // Nếu giỏ hàng không tồn tại, khởi tạo một giỏ hàng mới
            cart = new Cart();
            session.setAttribute("CART", cart);
        }

        Set<CartItem> listItem = cart.getCartItems();
        if (listItem == null || listItem.isEmpty()) {
            // Nếu giỏ hàng trống, truyền thông báo
            model.addAttribute("listItem", new ArrayList<>());
            model.addAttribute("message", "Giỏ hàng của bạn trống");
        } else {
            model.addAttribute("listItem", listItem);
            long total = 0;
            for (CartItem x : listItem) {
                total += (x.getQuantity() * x.getProduct().getPrice());
            }
            model.addAttribute("total", total);
            model.addAttribute("size", listItem.size());
        }

        return "client/pages/cart"; // Trả về view cart
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Hủy session
        return "redirect:/login-client"; // Chuyển hướng về trang đăng nhập
    }

    @GetMapping("/delete-cart-item/{cartItemId}")
    public String deleteCartItem(@PathVariable("cartItemId") Integer cartItemId, HttpSession session) {
        if (cartItemService.detete(cartItemId)) {
            Cart cart = (Cart) session.getAttribute("CART");

            if (cart != null) {
                CartItem cartItem = cart.getCartItems().stream()
                        .filter(item -> item.getId().equals(cartItemId))
                        .findFirst()
                        .orElse(null);

                if (cartItem != null) {
                    cart.getCartItems().remove(cartItem);
                    session.setAttribute("CART", cart);
                }
            }
        }

        return "client/pages/cart";
    }

    @PostMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable("productId") Integer productId, HttpSession session) {
        Product product = productService.findById(productId);
        if (product == null) {
            return "redirect:/products";
        }

        Cart cart = (Cart) session.getAttribute("CART");
        if (cart == null) {
            cart = new Cart();
        }

        CartItem existingItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            cartItemService.update(existingItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cart.addItem(cartItem);
            cartItem.setCart(cart);
            cartItemService.create(cartItem);
        }

        session.setAttribute("CART", cart);
        return "redirect:/products";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "client/pages/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            Model model) {

        if (userService.registerUser(username, password, email)) {
            return "client/pages/login";
        } else {
            model.addAttribute("error", "Tên người dùng đã tồn tại!");
            return "client/pages/register";
        }
    }

    @PostMapping("/cart/edit-cart-item")
    public String editCartItem(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity,
            HttpSession session) {
        CartItem cartItem = cartItemService.findById(id);
        cartItem.setQuantity(quantity);
        if (cartItemService.update(cartItem)) {

            Cart cart = (Cart) session.getAttribute("CART");
            Set<CartItem> cartItems = cart.getCartItems();
            Set<CartItem> newCartItems = new HashSet<CartItem>();
            for (CartItem x : cartItems) {
                if (x.getId() == id) {
                    CartItem ok = x;
                    ok.setQuantity(quantity);
                    newCartItems.add(ok);
                } else {
                    newCartItems.add(x);
                }
            }
            cart.setCartItems(newCartItems);
            session.setAttribute("CART", cart);
            return "redirect:/cart";
        } else {
            return "redirect:/cart";
        }
    }

    @GetMapping("/payment")
    public String getPayment(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("CART");

        if (cart == null) {
            // Nếu giỏ hàng không tồn tại, khởi tạo một giỏ hàng mới
            cart = new Cart();
            session.setAttribute("CART", cart);
        }

        Set<CartItem> listItem = cart.getCartItems();
        if (listItem == null || listItem.isEmpty()) {
            // Nếu giỏ hàng trống, truyền thông báo
            model.addAttribute("listItem", new ArrayList<>());
            model.addAttribute("message", "Giỏ hàng của bạn trống");
        } else {
            model.addAttribute("listItem", listItem);
            long total = 0;
            for (CartItem x : listItem) {
                total += (x.getQuantity() * x.getProduct().getPrice());
            }
            model.addAttribute("total", total);
            model.addAttribute("size", listItem.size());
        }

        Payment payment = new Payment();
        payment.setCartId(cart.getId());

        model.addAttribute("payment", payment);
        List<Category> listCategorys = this.categoryService.getAll();
        List<Category> categorys = new ArrayList<>();
        for (Category x : listCategorys) {
            if (x.getCategoryStatus() == true) {
                categorys.add(x);
            }
        }
        model.addAttribute("category", categorys);
        return "client/pages/payment";
    }

    @PostMapping("/payment")
    public String savePayment(@ModelAttribute("payment") Payment payment, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("CART");
        payment.setCartId(cart.getId());

        if (paymentService.create(payment)) {
            User user = userService.findByUserName((String) session.getAttribute("USERNAME"));

            Cart existingCart = user.getCart();
            if (existingCart != null) {
                existingCart.setUser(null);
                cartService.update(existingCart);
            }

            Cart newCart = new Cart();
            newCart.setUser(user);
            cartService.create(newCart);

            user.setCart(newCart);
            userService.update(user);

            session.setAttribute("CART", newCart);

            return "redirect:/products";
        }
        return "redirect:/payment";
    }

    @RequestMapping("/products/filter/{category}")
    public String productFilter(Model model, HttpSession session, @PathVariable("category") String category) {
        Cart cart = (Cart) session.getAttribute("CART");
        if (cart != null) {
            Set<CartItem> listItem = cart.getCartItems();
            System.out.println(listItem);
            model.addAttribute("listItem", listItem);
            long total = 0;
            for (CartItem x : listItem) {
                total += (x.getQuantity() * x.getProduct().getPrice());
            }
            model.addAttribute("total", total);
            model.addAttribute("size", listItem.size());
        } else {
            // Nếu giỏ hàng trống, truyền thông báo
            model.addAttribute("listItem", new ArrayList<>());
            model.addAttribute("message", "Giỏ hàng của bạn trống");
        }
        List<Product> list = this.productService.getAll();
        List<Product> listProducts = new ArrayList<>();
        for (Product x : list) {
            if (x.getStatus() == true && x.getCategory().getCategoryName().equals(category)) {
                listProducts.add(x);
            }
        }
        model.addAttribute("product", listProducts);
        List<Category> listCategorys = this.categoryService.getAll();
        List<Category> categorys = new ArrayList<>();
        for (Category x : listCategorys) {
            if (x.getCategoryStatus() == true) {
                categorys.add(x);
            }
        }
        model.addAttribute("category", categorys);
        return "client/pages/products-filter";
    }

    @GetMapping("/view/{id}")
    public String viewProduct(Model model, @PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        String desString = product.getDescription();
        String ok = "";
        for (int i = 3; i < desString.length() - 4; i++) {
            ok = ok + desString.charAt(i);
        }
        model.addAttribute("des", ok);
        model.addAttribute("product", product);
        List<Category> listCategorys = this.categoryService.getAll();
        List<Category> categorys = new ArrayList<>();
        for (Category x : listCategorys) {
            if (x.getCategoryStatus() == true) {
                categorys.add(x);
            }
        }
        model.addAttribute("category", categorys);
        return "client/pages/view";
    }

}
