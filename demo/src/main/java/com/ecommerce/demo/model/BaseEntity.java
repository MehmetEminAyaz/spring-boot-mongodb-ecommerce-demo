package com.ecommerce.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;

@Getter
@Setter
public class BaseEntity {

    @Id
    private String id;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant updatedAt;

    @Version
    private Long version;

}
