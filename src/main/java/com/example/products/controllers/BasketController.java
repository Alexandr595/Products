package com.example.products.controllers;

import com.example.products.models.Basket;
import com.example.products.services.BasketService;
import com.example.products.services.UserService;
import com.example.products.models.Product;
import com.example.products.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;
    private final UserService userService;

    @Autowired
    public BasketController(BasketService basketService, UserService userService) {
        this.basketService = basketService;
        this.userService = userService;
    }

    @GetMapping
    public Basket getBasket(Principal principal) {
        // Извлечение имени текущего пользователя
        String username = principal.getName();
        // Получение текущего пользователя
        User user = userService.findByUsername(username);
        // Возврат корзины для данного пользователя
        return basketService.getBasket(user);
    }

    @PostMapping
    public void addToBasket(@RequestBody Product product, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        basketService.addToBasket(user, product);
    }

    @DeleteMapping
    public void removeFromBasket(@RequestBody Product product, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        basketService.removeFromBasket(user, product);
    }

    @PutMapping
    public void updateBasket(@RequestBody List<Product> products, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        basketService.updateBasket(user, products);
    }
}