package com.abutua.product_backend.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.product_backend.models.ProductModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin
public class ProductController {

    private List<ProductModel> productModels = new ArrayList<>();

  

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

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProducts(@RequestBody ProductModel productModel) {
        productModel.setId(productModels.size() + 1);
        productModels.add(productModel);

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/products/{id}")
        .buildAndExpand(productModel.getId())
        .toUri();

        return ResponseEntity.created(location).body(productModel);
    }
    

    
}
