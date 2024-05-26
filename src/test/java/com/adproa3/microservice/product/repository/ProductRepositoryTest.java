package com.adproa3.microservice.product.repository;

import com.adproa3.microservice.product.model.Product;
import com.adproa3.microservice.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void findByProductNameContainingIgnoreCase() {

        Product product1 = new Product("baju", 199000.00, 30, 0, 0, "");
        productService.createProduct(product1);
        List<Product> productFound1 = productRepository.findByProductNameContainingIgnoreCase("baju");
        List<Product> productFound2 = productRepository.findByProductNameContainingIgnoreCase("ini-tidak-ada");
        assertThat(productFound1.size()).isEqualTo(1);
        assertThat(productFound1.getFirst().getProductName()).isEqualTo(product1.getProductName());
        assertThat(productFound2.size()).isEqualTo(0);
        productRepository.deleteAll();
    }
}