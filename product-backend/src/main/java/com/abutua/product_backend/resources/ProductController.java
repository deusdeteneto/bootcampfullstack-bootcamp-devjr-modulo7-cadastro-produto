package com.abutua.product_backend.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abutua.product_backend.models.ProductModel;

@RestController
public class ProductController {

    @GetMapping("/product")
    public ProductModel getProduct() {

        ProductModel productModel = new ProductModel();

        productModel.setId(1);
        productModel.setName("Product 01");
        productModel.setPrice(100.50);

        return productModel;
    }
    
    @GetMapping("/product-all")
    public List<ProductModel> getProductAll() {
        
        ProductModel productModel1 = new ProductModel();

        productModel1.setId(1);
        productModel1.setName("Product 01");
        productModel1.setPrice(100.50);

        
        ProductModel productModel2 = new ProductModel();

        productModel2.setId(2);
        productModel2.setName("Product 02");
        productModel2.setPrice(200);


        ProductModel productModel3 = new ProductModel();

        productModel3.setId(3);
        productModel3.setName("Product 03");
        productModel3.setPrice(170.10);


        List<ProductModel> listProductModel = new ArrayList<>();
        listProductModel.add(productModel1);
        listProductModel.add(productModel2);
        listProductModel.add(productModel3);
        
        return listProductModel;

    }
    
}
