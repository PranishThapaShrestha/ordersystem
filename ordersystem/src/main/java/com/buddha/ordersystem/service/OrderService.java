package com.buddha.ordersystem.service;

import com.buddha.ordersystem.entity.Order;
import com.buddha.ordersystem.entity.OrderItem;
import com.buddha.ordersystem.entity.Product;
import com.buddha.ordersystem.repository.OrderItemRepository;
import com.buddha.ordersystem.repository.OrderRepository;
import com.buddha.ordersystem.repository.ProductRepository;

import java.util.List;

public class OrderService {

    private final ProductRepository productRepo;
    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;

    public OrderService(ProductRepository productRepo, OrderRepository orderRepo, OrderItemRepository orderItemRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
    }
    public Order createOrder(List<Integer> productIds,List<Integer> quantities){
//        for (int i=0; i< productIds.size();i++){
//            Product product= productRepo.findById(productIds.get(i))
//                .orElseThrow(() -> new RuntimeException("Product not found!!"));
//            OrderItem orderItem=new OrderItem(product,)

//        }




        return new Order();
    }

}
