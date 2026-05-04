package com.ecommerce.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "products")
@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @Indexed
    private String sku;

    private String name;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    @Indexed
    private String categoryId;

    @Builder.Default
    private Map<String, Object> attributes = new HashMap<>();

    @Builder.Default
    private boolean isActive = true; // Soft delete için

    @Builder.Default
    private Integer stockQuantity = 0;





}
