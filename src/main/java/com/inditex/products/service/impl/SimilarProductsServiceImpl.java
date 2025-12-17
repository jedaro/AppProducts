package com.inditex.products.service.impl;

import com.inditex.products.exception.ProductNotFoundException;
import com.inditex.products.mockAPI.IMockAPIClient;
import com.inditex.products.model.ProductDetail;
import com.inditex.products.service.ISimilarProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SimilarProductsServiceImpl implements ISimilarProductsService {

    private  final IMockAPIClient iMockAPIClient;

    @Override
    public List<ProductDetail> getSimilarProducts(String productId) {

        try {
            log.info("Calling mock api client: {}", iMockAPIClient.getClass().getName());

            List<String> similarProductIds = iMockAPIClient.getSimilarProductIds(productId);
            List<ProductDetail> productDetailList = new ArrayList<>();

            similarProductIds.forEach(id -> {
                ProductDetail product = iMockAPIClient.getProductById(id);
                productDetailList.add(product);
            });


            return productDetailList;

        }catch (Exception e){
            log.debug("Error SimilarProductsServiceImpl: {}", e.getMessage());
            throw new ProductNotFoundException("Similar Product Ids fot found");
        }
    }
}
