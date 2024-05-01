package com.adproa3.microservice.product.controller;

import com.adproa3.microservice.product.model.DTO.SetProductDiscountDTO;
import com.adproa3.microservice.product.model.Product;
import com.adproa3.microservice.product.service.ProductService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
        Product productToBeEdited = productService.findOneById(product.getProductId());
        return productService.editProduct(productToBeEdited);
    }

    @GetMapping("/find-by-id")
    public Product findById(@RequestParam UUID id) {
        Product product = productService.findOneById(id);
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produk tidak ditemukan di database.");
        } else {
            return product;
        }
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam String query) {
        return productService.searchProducts(query);
    }

    @PostMapping("/set-discount")
    public Product setProductDiscount(@RequestBody SetProductDiscountDTO setProductDiscountDTO) {
        return productService.setProductDiscount(setProductDiscountDTO);
    }

}
