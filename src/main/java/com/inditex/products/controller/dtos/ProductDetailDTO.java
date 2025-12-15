package com.inditex.products.controller.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "Information related detail product")
public class ProductDetailDTO {

    @Schema(description = "Product id")
    private String id;

    @Schema(description = "Product's name")
    private String name;

    @Schema(description = "Price's product")
    private Double price;

    @Schema(description = "Flag related if product is available")
    private Boolean availability;
}
