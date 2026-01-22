package com.buddha.ordersystem.dto;

public record OrderItemDto(ProductDto productDto, Long quantity, double price) {
}
