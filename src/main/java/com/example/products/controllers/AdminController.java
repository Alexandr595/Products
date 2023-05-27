package com.example.products.controllers;

import org.springframework.ui.Model;
import com.example.products.models.Product;
import com.example.products.models.User;
import com.example.products.services.ProductService;
import com.example.products.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public AdminController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/users2")
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users2", users);
        return "admin/users2";
    }

    @GetMapping("/products2")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products2", products);
        return "admin/products2";
    }

    // Другие методы для добавления, редактирования и удаления продуктов...
}