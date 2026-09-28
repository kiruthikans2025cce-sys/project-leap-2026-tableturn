package com.example.demo.service;

import com.example.demo.entity.FoodOrder;
import com.example.demo.entity.RestaurantTable;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TableRepository tableRepository;

    public FoodOrder placeOrder(FoodOrder order) {

        RestaurantTable table =
                tableRepository.findById(
                                order.getTable().getTableId())
                        .orElseThrow();

        table.setStatus("OCCUPIED");
        tableRepository.save(table);

        return orderRepository.save(order);
    }
}