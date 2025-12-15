package com.inditex.products.controller.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Schema(description = "Information related list of similar products")
public class SimilarProductsResponseDTO {

    @Schema(description = "List similar products")
    List<ProductDetailDTO> productDetailDTOs;
}
