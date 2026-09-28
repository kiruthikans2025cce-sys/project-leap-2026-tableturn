package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private TableRepository tableRepository;

    public Bill generateBill(Long orderId) {

        FoodOrder order =
                orderRepository.findById(orderId)
                        .orElseThrow();

        double total = 0;

        for (OrderItem item : order.getItems()) {
            total += item.getPrice() * item.getQuantity();
        }

        Bill bill = new Bill();
        bill.setTable(order.getTable());
        bill.setTotalAmount(total);

        order.getTable().setStatus("FREE");
        tableRepository.save(order.getTable());

        return billRepository.save(bill);
    }
}