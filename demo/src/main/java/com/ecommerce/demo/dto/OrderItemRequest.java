package com.ecommerce.demo.dto;

public record OrderItemRequest(
        String sku,
        Integer quantity
) {}
