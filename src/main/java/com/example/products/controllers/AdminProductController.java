package com.example.products.controllers;

import com.example.products.models.Product;
import com.example.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminProductController {

    private ProductService productService;

    @Autowired
    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "admin/products"; // это должно вести к Thymeleaf шаблону, представляющему все продукты.
    }

    @GetMapping("/admin/products/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductId(id);
        if (product == null) {
            return "redirect:/admin/products";
        }
        model.addAttribute("product", product);
        return "admin/edit_product"; // это должно вести к Thymeleaf шаблону для редактирования продукта.
    }

    @PostMapping("/admin/products/edit/{id}")
    public String updateProduct(@PathVariable("id") int id, Product product) {
        productService.editProduct(id, product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "admin/new_product"; // это должно вести к Thymeleaf шаблону для создания нового продукта.
    }

    @PostMapping("/admin/products/new")
    public String createProduct(Product product) {
        productService.newProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }
}