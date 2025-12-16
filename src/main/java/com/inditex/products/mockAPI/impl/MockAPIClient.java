package com.inditex.products.mockAPI.impl;

import com.inditex.products.mockAPI.IMockAPIClient;
import com.inditex.products.model.ProductDetail;
import com.inditex.products.model.SimilarProductId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MockAPIClient implements IMockAPIClient {

    @Value("${mocks.server.url}")
    private String MOCK_SERVER_API;

    private static final String URI_MOCK_API_DETAIL = "/{productId}";
    private static final String URI_MOCK_API_SIMILAR_IDS = "/{productId}/similarids";

    @Override
    public ProductDetail getProductById(String productId) {
        RestTemplate restTemplate = getRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.exchange(MOCK_SERVER_API+URI_MOCK_API_DETAIL,
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                new ParameterizedTypeReference<ProductDetail>(){},
                productId).getBody();
    }

    @Override
    public List<String> getSimilarProductIds(String productId) {
        RestTemplate restTemplate = getRestTemplate();
        return restTemplate.exchange(MOCK_SERVER_API+URI_MOCK_API_SIMILAR_IDS,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>(){},
                productId).getBody();
    }

    private RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
