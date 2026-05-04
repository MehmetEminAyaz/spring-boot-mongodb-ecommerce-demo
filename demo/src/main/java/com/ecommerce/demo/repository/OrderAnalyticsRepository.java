package com.ecommerce.demo.repository;

import com.ecommerce.demo.dto.CategoryReportResponse;
import com.ecommerce.demo.dto.OrderSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderAnalyticsRepository {

    private final MongoTemplate mongoTemplate;

    public OrderSummaryResponse getGeneralOrderSummary(){

        Aggregation aggregation=Aggregation.newAggregation(
                Aggregation.group()
                        .sum("totalPrice").as("totalRevenue")
                        .count().as("totalOrders")
        );

        AggregationResults<OrderSummaryResponse> results = mongoTemplate.aggregate(
                aggregation, "orders", OrderSummaryResponse.class
        );

        return results.getUniqueMappedResult() != null
                ? results.getUniqueMappedResult()
                : new OrderSummaryResponse(java.math.BigDecimal.ZERO, 0L);
    }
}
