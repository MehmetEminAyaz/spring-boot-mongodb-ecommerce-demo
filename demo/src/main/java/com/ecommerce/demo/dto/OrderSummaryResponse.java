package com.ecommerce.demo.dto;

import java.math.BigDecimal;

public record OrderSummaryResponse(
        BigDecimal totalRevenue,
        Long totalOrders
) {
}
