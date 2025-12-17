package com.inditex.products.controller;

import com.inditex.products.exception.ProductNotFoundException;
import com.inditex.products.model.ProductDetail;
import com.inditex.products.model.SimilarProducts;
import com.inditex.products.service.impl.SimilarProductsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimilarProductsControllerTest {

    @Mock
    private SimilarProductsServiceImpl similarProductsService;

    @InjectMocks
    private SimilarProductsController controller;

    @Test
    @DisplayName(
            "Given a path parameter productId" +
                    "When call get api similar products" +
                    "Then return SimilarProducts list")
    public void givenAProductIdValid_whenCallApi_thenReturnSimilarProductsList(){
        // Given
        List<ProductDetail> productDetailList = List.of(
                ProductDetail.builder()
                        .id("1")
                        .name("Pants")
                        .price(20.2)
                        .availability(Boolean.TRUE)
                        .build());
        // When
        when(similarProductsService.getSimilarProducts(anyString())).thenReturn(productDetailList);
        ResponseEntity<SimilarProducts> response = controller.getProductSimilar(anyString());

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    @DisplayName(
            "Given a path parameter productId invalid" +
                    "When call get api similar products" +
                    "Then return 404 error code")
    public void givenAProductIdInvalid_whenCallApi_thenReturnException404(){
        // Given
        List<ProductDetail> productDetailList = List.of(
                ProductDetail.builder()
                        .id("1")
                        .name("Pants")
                        .price(20.2)
                        .availability(Boolean.TRUE)
                        .build());
        // When
        when(similarProductsService.getSimilarProducts(anyString())).thenThrow(new ProductNotFoundException("Error"));

        Assertions.assertThrows(ProductNotFoundException.class, () -> controller.getProductSimilar(anyString()));

    }
}
