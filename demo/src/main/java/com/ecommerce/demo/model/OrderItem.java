package com.ecommerce.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter@Setter
@Builder
public class OrderItem {

    private String productId;
    private String sku;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice; //quantity*unit price
}
