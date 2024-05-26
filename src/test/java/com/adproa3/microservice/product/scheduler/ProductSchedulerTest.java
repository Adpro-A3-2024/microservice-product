package com.adproa3.microservice.product.scheduler;

import com.adproa3.microservice.product.model.Product;
import com.adproa3.microservice.product.repository.ProductRepository;
import com.adproa3.microservice.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductSchedulerTest {

    @Autowired
    private ProductScheduler scheduler;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void decrementDiscountDaysCronJob() {
        Product product1 = new Product("baju", 199000.00, 30, 0, 0, "");
        Product product2 = new Product("celana", 50000.00, 15, 0, 0, "");

        product1.setProductDiscountDaysLeft(5);
        product1.setProductDiscount(10);
        product2.setProductDiscountDaysLeft(0);
        product2.setProductDiscount(0);
        productService.createProduct(product1);
        productService.createProduct(product2);

        scheduler.decrementDiscountDaysCronJob();

        assertEquals(product1.getProductDiscountDaysLeft()-1, productService.findOneById(product1.getProductId()).getProductDiscountDaysLeft());
        assertEquals(0, productService.findOneById(product2.getProductId()).getProductDiscountDaysLeft());

        productRepository.deleteAll();
    }
}