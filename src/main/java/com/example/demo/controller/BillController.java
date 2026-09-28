package com.example.demo.controller;

import com.example.demo.entity.Bill;
import com.example.demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService service;

    @PostMapping("/{orderId}")
    public Bill generate(@PathVariable Long orderId) {
        return service.generateBill(orderId);
    }
}