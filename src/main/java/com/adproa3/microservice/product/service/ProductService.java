package com.adproa3.microservice.product.service;


import com.adproa3.microservice.product.model.Product;
import java.util.List;

public interface ProductService {
    public Product createProduct(Product product);
    public List<Product> getAllProducts();
    public Product editProduct(Product product);
}
