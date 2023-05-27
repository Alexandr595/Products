package com.example.products.repositories;

import com.example.products.models.Basket;
import com.example.products.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findByUser(User user);
}