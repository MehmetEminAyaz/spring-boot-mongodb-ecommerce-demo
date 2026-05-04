package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.OrderItemRequest;
import com.ecommerce.demo.dto.OrderRequest;
import com.ecommerce.demo.dto.OrderSummaryResponse;
import com.ecommerce.demo.model.Order;
import com.ecommerce.demo.model.OrderItem;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.OrderAnalyticsRepository;
import com.ecommerce.demo.repository.OrderRepository;
import com.ecommerce.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderAnalyticsRepository orderAnalyticsRepository;

    public Order createOrder(OrderRequest request){
        List<OrderItem> orderItems=new ArrayList<>();
        BigDecimal orderTotalAmount=BigDecimal.ZERO;

        List<Product> updatedProducts=new ArrayList<>();

        for(OrderItemRequest itemRequest:request.items()){
            //Ürünü sku'dan bul
            Product product=productRepository.findBySku(itemRequest.sku())
                    .orElseThrow(()->new RuntimeException("Ürün bulunamadı: "+itemRequest.sku()));

            //Stok kontrolü
            if(product.getStockQuantity()<itemRequest.quantity()){
                throw new RuntimeException("Yetersiz stok! Ürün: " + product.getName() +
                        " Mevcut Stok: " + product.getStockQuantity());
            }

            //stok düşür
            product.setStockQuantity(product.getStockQuantity()-itemRequest.quantity());
            //güncellenecekler listesine ekle
            updatedProducts.add(product);

            BigDecimal itemTotal=product.getPrice().multiply(BigDecimal.valueOf(itemRequest.quantity()));


            //orderItem oluştur
            OrderItem orderItem = OrderItem.builder()
                    .productId(product.getId())
                    .sku(product.getSku())
                    .productName(product.getName())
                    .unitPrice(product.getPrice())
                    .quantity(itemRequest.quantity())
                    .totalPrice(itemTotal)
                    .build();

             orderItems.add(orderItem);
             orderTotalAmount=orderTotalAmount.add(itemTotal);
        }

        productRepository.saveAll(updatedProducts);

        //order oluştur
        Order order = Order.builder()
                .orderNumber("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .customerId(request.customerId())
                .items(orderItems)
                .totalPrice(orderTotalAmount)
                .build();

        return orderRepository.save(order);
    }

    public OrderSummaryResponse getOrderSummary() {
        return orderAnalyticsRepository.getGeneralOrderSummary();
    }
}
