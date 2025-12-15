package com.inditex.products.controller;

import org.springframework.web.bind.annotation.RestController;

import com.inditex.products.controller.dtos.ProductResponseDto;
import com.inditex.products.service.IProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class ProductController {

    private IProductService iProductService;


    public ProductResponseDto retrieveProduct(){

        log.info("Calling controller: ", this.getClass().getName());

        return ProductResponseDto.builder().build();
    }


}
