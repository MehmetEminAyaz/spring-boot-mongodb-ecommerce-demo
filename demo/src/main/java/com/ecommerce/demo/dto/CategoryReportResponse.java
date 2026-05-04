package com.ecommerce.demo.dto;

import java.math.BigDecimal;

public record CategoryReportResponse(
        String _id,
        Integer totalStock,
        BigDecimal averagePrice
) {
}
