package com.ecommerce.demo.controller;

import com.ecommerce.demo.dto.CategoryReportResponse;
import com.ecommerce.demo.model.Product;
import com.ecommerce.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product savedProduct=productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/analytics/categories")
    public ResponseEntity<List<CategoryReportResponse>> getCategoryAnalytics(){
        return ResponseEntity.ok(productService.getCategoryAnalytics());
    }


}
