package com.example.demo.controller;

import com.example.demo.entity.FoodOrder;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public FoodOrder placeOrder(@RequestBody FoodOrder order) {
        return service.placeOrder(order);
    }
}