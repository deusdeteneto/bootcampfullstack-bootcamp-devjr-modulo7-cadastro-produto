package com.abutua.product_backend.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.product_backend.models.ProductModel;


@RestController
public class ProductController {

    private List<ProductModel> productModels = Arrays.asList(
        new ProductModel(1, "Product 01", "Computador Gamer Verde Java", 100.50, 1, false, false),
        new ProductModel(2, "Product 02", "Computador Gamer Vermelho Lion", 200.50, 2, true, true),
        new ProductModel(3, "Product 03", "Computador Gamer Branco Tiger", 300.50, 3, false, true)
    );

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductModel> getProducts(@PathVariable int id) {

        ProductModel productModel = productModels.stream()
        .filter(p -> p.getId() == id)
        .findFirst()
        .orElseThrow(() -> new ResponseStatusException (HttpStatus.NOT_FOUND, "Product not found"));
        return ResponseEntity.ok(productModel);
    }
    
    @GetMapping("/products")
    public List<ProductModel> getAllProducts() {
        return productModels;
        
    }

    
}
