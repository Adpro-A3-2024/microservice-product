package com.adproa3.microservice.product.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class SetProductDiscountDTO {
    private UUID productId;
    private double discount;
    private int discountDays;
}
