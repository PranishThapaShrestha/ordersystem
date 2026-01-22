package com.buddha.ordersystem.service;

import com.buddha.ordersystem.dto.OrderDto;
import com.buddha.ordersystem.dto.OrderItemDto;
import com.buddha.ordersystem.dto.ProductDto;
import com.buddha.ordersystem.entity.Order;
import com.buddha.ordersystem.entity.OrderItem;
import com.buddha.ordersystem.entity.Product;
import com.buddha.ordersystem.repository.OrderItemRepository;
import com.buddha.ordersystem.repository.OrderRepository;
import com.buddha.ordersystem.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    private final ProductRepository productRepo;
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;

    public OrderService(ProductRepository productRepo, OrderRepository orderRepo, OrderItemRepository orderItemRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
    }

    public Order createOrder(List<Long> productIds, List<Long> quantities) {
        if (productIds.size() != quantities.size()) {
            throw new IllegalArgumentException("Products and quantities size mismatch");
        }

        Order order = new Order();

        for (int i = 0; i < productIds.size(); i++) {
            Product product = productRepo.findById(productIds.get(i))
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            OrderItem item = new OrderItem(product, quantities.get(i), product.getPrice());
            order.addItem(item);
        }

        orderRepo.save(order);



        return new Order();
    }

    private OrderDto maoToDto(Order order){
        List<OrderItemDto> intems= order.getOrderItems().stream()
                .map(i ->new OrderItemDto(
                        new ProductDto(i.getProduct().getId(),i.getProduct().getProductName()
                        ,i.getProduct().getPrice()),i.getQuantity(),i.getPrice())).toList();

        return new OrderDto(order.getId(),order.getDate(),intems);


    }



}
