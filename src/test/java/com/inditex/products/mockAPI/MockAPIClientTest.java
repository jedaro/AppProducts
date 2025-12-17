package com.inditex.products.mockAPI;

import com.inditex.products.mockAPI.impl.MockAPIClient;
import com.inditex.products.model.ProductDetail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MockAPIClientTest {

    @InjectMocks
    private MockAPIClient client;

    @Mock
    private RestTemplate restTemplate;

    @Test
    @DisplayName(
            "Given a productId parameter" +
                    "When call get product detail from mock client " +
                    "Then return ProductDetail")
    public void givenAProductId_whenCallMockApi_thenReturnProductDetail(){
        // Given
        String productId = "1";
        ProductDetail productDetail = ProductDetail.builder()
                        .id("1")
                        .name("Pants")
                        .price(20.2)
                        .availability(Boolean.TRUE)
                        .build();

        ReflectionTestUtils.setField(client, "MOCK_SERVER_API", "http://localhost:3001/product");

        // When
        Mockito.when(restTemplate.exchange("http://localhost:5001/product/{productId}",
                HttpMethod.GET,
                null,
                ProductDetail.class, productId)
        ).thenReturn(new ResponseEntity<>(productDetail, HttpStatus.OK));

        ProductDetail productResponse = client.getProductById(productId);

        // Then
        Assertions.assertNotNull(productResponse);
        Assertions.assertEquals(productId, productResponse.getId());
    }

    @Test
    @DisplayName(
            "Given a productId parameter" +
                    "When call get similar products API mock " +
                    "Then return list ids of products")
    public void givenAProductId_whenCallMockApi_thenReturnListProductIds(){
        // Given
        String productId = "1";

        List<String> listIds = List.of("1", "2", "3");

        ReflectionTestUtils.setField(client, "MOCK_SERVER_API", "http://localhost:3001/product");

        // When
        Mockito.when(restTemplate.exchange("http://localhost:5001/product/{productId}/similarids",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>(){},
                productId)
        ).thenReturn(new ResponseEntity<>(listIds, HttpStatus.OK));

        List<String> response = client.getSimilarProductIds(productId);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(listIds.size(), response.size());
    }
}
