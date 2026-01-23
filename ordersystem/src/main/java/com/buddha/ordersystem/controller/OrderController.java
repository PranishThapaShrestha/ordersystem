package com.buddha.ordersystem.controller;

import com.buddha.ordersystem.dto.OrderDto;
import com.buddha.ordersystem.entity.OrderItem;
import com.buddha.ordersystem.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/create")
    public OrderDto createOrder(@RequestParam List<Long> productIds, @RequestParam List<Long> quantities) {
        return orderService.createOrder(productIds, quantities);

    }



    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.findAllOrders();
    }


}
