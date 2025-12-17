package com.inditex.products.service;

import com.inditex.products.exception.ProductNotFoundException;
import com.inditex.products.mockAPI.impl.MockAPIClient;
import com.inditex.products.model.ProductDetail;
import com.inditex.products.service.impl.SimilarProductsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimilarProductsServiceImplTest {

    @InjectMocks
    private SimilarProductsServiceImpl service;

    @Mock
    private MockAPIClient mockAPIClient;

    @Test
    @DisplayName(
            "Given a productId parameter" +
                    "When call service getSimilarProducts " +
                    "Then return list of ProductDetail")
    public void givenAProductId_whenCallService_thenReturnProductDetailList(){
        // Given
        String productId = "1";

        ProductDetail productDetail = ProductDetail.builder()
                .id(productId)
                .name("Pants")
                .price(20.2)
                .availability(Boolean.TRUE)
                .build();

        List<String> listIds = List.of("4", "2", "3");

        // When
        when(mockAPIClient.getSimilarProductIds(anyString())).thenReturn(listIds);
        when(mockAPIClient.getProductById(anyString())).thenReturn(productDetail);

        List<ProductDetail> productResponse = service.getSimilarProducts(productId);

        // Then
        Assertions.assertNotNull(productResponse);
        Assertions.assertEquals(productDetail.getId(), productResponse.get(0).getId());
    }

    @Test
    @DisplayName(
            "Given a productId parameter invalid" +
                    "When call service getSimilarProducts " +
                    "Then return exception")
    public void givenAProductIdInvalid_whenCallService_thenReturnPException(){
        // When-then
        when(mockAPIClient.getSimilarProductIds(anyString())).thenThrow(new ProductNotFoundException("Error API"));

        Assertions.assertThrows(
                ProductNotFoundException.class, () -> service.getSimilarProducts(null)
        );
    }
}
