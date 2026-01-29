package com.buddha.ordersystem.controller;

import com.buddha.ordersystem.dto.ProductDto;
import com.buddha.ordersystem.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllOrders() {
        return productService.findAllProducts();
    }

    @GetMapping("/ids")
    public List<ProductDto> getOrdersByIds(@RequestParam List<Long> productIds) {
        return productService.getAllProductsByIds(productIds);
    }

}
