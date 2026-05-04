package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.OrderRequest;
import com.ecommerce.demo.dto.OrderSummaryResponse;
import com.ecommerce.demo.model.Order;
import com.ecommerce.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request){
        Order savedOrder=orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping("/analytics/summary")
    public ResponseEntity<OrderSummaryResponse> getOrderSummary() {
        return ResponseEntity.ok(orderService.getOrderSummary());
    }
}
