package com.adproa3.microservice.product.scheduler;

import com.adproa3.microservice.product.model.Product;
import com.adproa3.microservice.product.repository.ProductRepository;
import com.adproa3.microservice.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductScheduler {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void decrementDiscountDaysCronJob() {
        List<Product> products = productService.getAllProducts();

        for(Product product : products) {
            int productDiscountDaysLeft = product.getProductDiscountDaysLeft();
            if(productDiscountDaysLeft > 0) {
                product.setProductDiscountDaysLeft(productDiscountDaysLeft-1);
                if(product.getProductDiscountDaysLeft() == 0) {
                    product.setProductDiscount(0);
                }
                productRepository.save(product);
            }
        }
    }
}
