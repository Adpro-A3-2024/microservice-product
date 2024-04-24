package com.adproa3.microservice.product.repository;

import com.adproa3.microservice.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
