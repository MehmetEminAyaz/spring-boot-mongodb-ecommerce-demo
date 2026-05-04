package com.ecommerce.demo.repository;

import com.ecommerce.demo.dto.CategoryReportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@RequiredArgsConstructor
public class ProductAnalyticsRepository {
    private final MongoTemplate mongoTemplate;

    //Kategori bazlı stok ve fiyat analizi
    public List<CategoryReportResponse> getCategoryReport(){

        Aggregation aggregation = newAggregation(
                //  Sadece aktif ürünleri işleme al
                match(org.springframework.data.mongodb.core.query.Criteria.where("isActive").is(true)),

                //  Kategori ID'sine göre grupla
                group("categoryId")
                        .sum("stockQuantity").as("totalStock") // Stokları topla
                        .avg("price").as("averagePrice")       // Fiyatların ortalamasını al
        );


        // products koleksiyonunda çalıştır ve sonucu CategoryReportResponse DTO'suna map et
        AggregationResults<CategoryReportResponse> results = mongoTemplate.aggregate(
                aggregation, "products", CategoryReportResponse.class
        );

        // Sonucu döndür
        return results.getMappedResults();
    }
}
