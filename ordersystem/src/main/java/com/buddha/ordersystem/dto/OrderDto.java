package com.buddha.ordersystem.dto;

import java.time.LocalDate;
import java.util.List;

public record OrderDto(Long id, LocalDate date, List<OrderItemDto> orderItemDto) {
}
