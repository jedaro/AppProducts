package com.inditex.products.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "Information related detail product")
public class ProductDetail {

    @Schema(description = "Product id")
    private String id;

    @Schema(description = "Product's name")
    private String name;

    @Schema(description = "Price's product")
    private Double price;

    @Schema(description = "Flag related if product is available")
    private Boolean availability;

    public ProductDetail() {
        super();
    }
}
