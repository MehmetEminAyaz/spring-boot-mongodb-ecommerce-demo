package com.ecommerce.demo.model;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "orders")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity {

    @Indexed
    private String orderNumber;

    @Indexed
    private String customerId;

    private List<OrderItem> items;
    @Field(targetType = org.springframework.data.mongodb.core.mapping.FieldType.DECIMAL128)
    private BigDecimal totalPrice;

    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;
}
