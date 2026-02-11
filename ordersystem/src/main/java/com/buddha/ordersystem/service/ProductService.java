package com.buddha.ordersystem.service;

import com.buddha.ordersystem.dto.ProductDto;
import com.buddha.ordersystem.entity.Product;
import com.buddha.ordersystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<ProductDto> findAllProducts() {

        return productRepo.findAll().stream().map(this::mapToProductDto).toList();
    }


    public List<ProductDto> getAllProductsByIds(List<Long> productIds) {
        return productRepo.findAllById(productIds).stream().map(product -> mapToProductDto(product)).toList();
    }

    private ProductDto mapToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getProductName(), product.getPrice());
    }


}
