package com.adproa3.microservice.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    Integer productId;

    @NotNull(message = "Nama produk tidak boleh bernilai null.")
    @NotBlank(message = "Nama produk tidak boleh kosong.")
    String productName;

    @NotNull(message = "Harga produk tidak boleh bernilai null.")
    @NotBlank(message = "Harga produk tidak boleh kosong.")
    double productPrice;

    @NotNull(message = "Stok tidak boleh bernilai null.")
    @NotBlank(message = "Stok tidak boleh kosong.")
    int productStock;

    double productDiscount = 0.0;
    int productDiscountDaysLeft = 0;
    String productPictureUrl;

    public Product(String productName, double productPrice, int productStock, double productDiscount, int productDiscountDaysLeft, String productPictureUrl) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDiscount = productDiscount;
        this.productDiscountDaysLeft = productDiscountDaysLeft;
        this.productPictureUrl = productPictureUrl;
    }

}
