package com.inditex.products.controller;

import com.inditex.products.controller.dtos.ProductDetailDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.products.controller.dtos.SimilarProductsResponseDTO;
import com.inditex.products.mappers.ProductMapper;
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

    
    @GetMapping("/product/{productId}/similar")
    public ResponseEntity<SimilarProductsResponseDTO> getSimilarProduct(@PathVariable(value = "productId", required = true) String productId){

        log.info("Calling controller: {}", this.getClass().getName());
        SimilarProductsResponseDTO  responseDTO = SimilarProductsResponseDTO.builder()
                .productDetailDTOs(List.of(ProductDetailDTO.builder()
                        .id("4")
                        .name("Boots")
                        .price(39.99)
                        .availability(Boolean.TRUE)
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }


}
