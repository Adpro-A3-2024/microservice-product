package com.adproa3.microservice.product.controller;

import com.adproa3.microservice.product.model.Product;
import com.adproa3.microservice.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public List<Product> findAll() {
        return productService.getAllProducts();
    }

    @PostMapping("/create-product")
    public Product createProductPost(@RequestBody @Validated Product product) {
        return productService.createProduct(product);
    }

    @PostMapping("/edit-product")
    public Product editProductPost(@RequestBody @Validated Product product) {
        return productService.editProduct(product);
    }
}
