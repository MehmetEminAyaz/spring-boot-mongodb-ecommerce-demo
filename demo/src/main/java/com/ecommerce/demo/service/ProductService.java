package com.ecommerce.demo.service;

import com.ecommerce.demo.dto.CategoryReportResponse;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.repository.ProductAnalyticsRepository;
import com.ecommerce.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductAnalyticsRepository productAnalyticsRepository;

    public Product createProduct(Product product){

        //logic gelicek
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<CategoryReportResponse> getCategoryAnalytics(){
        return productAnalyticsRepository.getCategoryReport();
    }
}
