package com.example.products.services;

import com.example.products.models.Basket;
import com.example.products.models.Product;
import com.example.products.models.User;
import com.example.products.repositories.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BasketService {
    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public Basket getBasket(User user) {
        return basketRepository.findByUser(user)
                .orElseGet(() -> {
                    Basket basket = new Basket();
                    basket.setUser(user);
                    return basketRepository.save(basket);
                });
    }

    @Transactional
    public void addToBasket(User user, Product product) {
        Basket basket = getBasket(user);
        basket.getProducts().add(product);
        basketRepository.save(basket);
    }

    @Transactional
    public void removeFromBasket(User user, Product product) {
        Basket basket = getBasket(user);
        basket.getProducts().remove(product);
        basketRepository.save(basket);
    }

    @Transactional
    public void updateBasket(User user, Iterable<Product> products) {
        Basket basket = getBasket(user);
        basket.getProducts().clear();
        products.forEach(basket.getProducts()::add);
        basketRepository.save(basket);
    }
}