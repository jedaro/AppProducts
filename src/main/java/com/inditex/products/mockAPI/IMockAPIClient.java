package com.inditex.products.mockAPI;

import com.inditex.products.model.ProductDetail;
import com.inditex.products.model.SimilarProductId;

import java.util.List;

public interface IMockAPIClient {

    /**
     * Retrieve product detail from Mock API
     * @param productId product id
     * @return ProductDetail
     */
    public ProductDetail getProductById(String productId);

    /**
     * Get list similar product ids from Mock API
     *
     * @param productId product id
     * @return List<String>
     */
    public List<String> getSimilarProductIds(String productId);

}
