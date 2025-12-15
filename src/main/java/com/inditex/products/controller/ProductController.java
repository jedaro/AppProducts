package com.inditex.products.controller;

import com.inditex.products.controller.dtos.ProductDetailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.products.controller.dtos.SimilarProducts;
import com.inditex.products.service.IProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
public class ProductController {

    private IProductService iProductService;
    //private ProductMapper mapper;

    @Operation(
            summary = "Get similar products",
            operationId = "get-product-similar",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SimilarProducts.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Product Not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )

            }


    )
    @GetMapping("/product/{productId}/similar")
    public ResponseEntity<SimilarProducts> getSimilarProduct(@PathVariable(value = "productId", required = true) String productId){

        log.info("Calling controller: {}", this.getClass().getName());
        SimilarProducts responseDTO = SimilarProducts.builder()
                .similarProducts(List.of(ProductDetailDTO.builder()
                        .id("4")
                        .name("Boots")
                        .price(39.99)
                        .availability(Boolean.TRUE)
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }


}
