package com.ecommerce.demo.dto;

import java.util.List;

public record OrderRequest(
        String customerId,
        List<OrderItemRequest> items
){}
