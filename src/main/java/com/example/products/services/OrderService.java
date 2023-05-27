package com.example.products.services;

import com.example.products.models.Order;
import com.example.products.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order updateOrder(Long id, Order newOrder) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setProducts(newOrder.getProducts());
                    order.setUser(newOrder.getUser());
                    // set any other fields that should be updated
                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    newOrder.setId(id);
                    return orderRepository.save(newOrder);
                });
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}