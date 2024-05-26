package com.adproa3.microservice.product.controller;

import com.adproa3.microservice.product.model.Product;
import com.adproa3.microservice.product.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ProductControllerTest {

    private Product product;
    @Autowired
    private MockMvc mvc;


    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        product = new Product("baju", 199000.00, 30, 0, 0, "");
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        product = null;
    }

    @Test
    void testFindAll() throws Exception {
        Product product1 = new Product("baju", 199000.00, 30, 0, 0, "");
        Product product2 = new Product("celana", 50000.00, 15, 0, 0, "");
        productRepository.save(product1);
        productRepository.save(product2);
        List<Product> productBaseList = new ArrayList<>();
        productBaseList.add(product1);
        productBaseList.add(product2);
        List<Product> productQueriedList = (List<Product>) productRepository.findAll();
        assertEquals(productBaseList, productQueriedList);
        mvc.perform(MockMvcRequestBuilders
                        .get("/get-all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0].productId", is(product1.getProductId().toString())))
                .andExpect(jsonPath("$[0].productName", is(product1.getProductName())))
                .andExpect(jsonPath("$[1].productId", is(product2.getProductId().toString())))
                .andExpect(jsonPath("$[1].productName", is(product2.getProductName())));
    }

    @Test
    void testCreateProductPost() throws Exception {
        productRepository.save(product);
        Product fetchedProduct = productRepository.findById(product.getProductId()).get();
        assertEquals(product.getProductId(), fetchedProduct.getProductId());

        mvc.perform( MockMvcRequestBuilders
                        .post("/create-product")
                        .content(asJsonString(product))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(product.getProductId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value(product.getProductName()));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//    @Test
//    void testEditProductPost() {
//
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void searchProduct() {
//    }
//
//    @Test
//    void setProductDiscount() {
//    }
}