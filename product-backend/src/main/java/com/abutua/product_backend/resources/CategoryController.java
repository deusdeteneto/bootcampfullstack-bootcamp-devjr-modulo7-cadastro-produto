package com.abutua.product_backend.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.product_backend.models.CategoryModel;


@RestController
@CrossOrigin
public class CategoryController {
    
    private List<CategoryModel> categoryModels = Arrays.asList(
        new CategoryModel(1, "Produção Própria"),
        new CategoryModel(2, "Nacional"),
        new CategoryModel(3, "Importado"),
        new CategoryModel(4, "Premium")
    );

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryModel> getCategories(@PathVariable int id) {

        CategoryModel categoryModel = categoryModels.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categorie not found"));
        return ResponseEntity.ok(categoryModel);

    }
    
    @GetMapping("/categories")
    public List<CategoryModel> getAllCategories() {
        return categoryModels;
    }
    

    

}
