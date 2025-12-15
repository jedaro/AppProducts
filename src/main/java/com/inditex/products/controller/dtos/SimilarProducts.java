package com.inditex.products.controller.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "List of similar products to a given one ordered by similarity")
public class SimilarProducts {

    @Schema(description = "List similar products")
    List<ProductDetailDTO> similarProducts;
}
