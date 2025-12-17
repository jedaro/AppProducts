package com.inditex.products.service;

import com.inditex.products.model.ProductDetail;

import java.util.List;

public interface ISimilarProductsService {

    /**
     * Retrieves list similar products
     * @param productId product id
     * @return List<ProductDetail>
     */
    public List<ProductDetail> getSimilarProducts(String productId);
}
