package com.inditex.products.controller;

import com.inditex.products.AppProductsApplication;
import com.inditex.products.exception.ProductNotFoundException;
import com.inditex.products.mockAPI.impl.MockAPIClient;
import com.inditex.products.model.ProductDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {AppProductsApplication.class})
@TestPropertySource(properties = {
        "mocks.server.url=http://localhost:3001/product"
})
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class SimilarProductsControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MockAPIClient mockAPIClient;

    private static final  String BASE_URL = "http://localhost:%d";

    @LocalServerPort
    int SERVER_PORT;

    private String getServiceUrl(){
        return String.format(BASE_URL + "/product/{productId}/similar", SERVER_PORT);
    }


    private void setup(){
        when(mockAPIClient.getSimilarProductIds(anyString())).thenReturn(buildListIds());
        when(mockAPIClient.getProductById(anyString())).thenReturn(buildProductDetail());
    }

    private List<String> buildListIds(){
        return List.of("2");
    }

    private ProductDetail buildProductDetail(){
        return ProductDetail
                .builder()
                .id("1")
                .name("SHIRT")
                .price(30.2)
                .availability(Boolean.TRUE)
                .build();
    }



    @Test
    @DisplayName(
            "Given a path parameter productId" +
                    "When call get api similar products" +
                    "Then return SimilarProducts list")
    public void givenAProductIdValid_whenCallApi_thenReturnSimilarProductsList() throws Exception {
        setup();
        mockMvc.perform(get(getServiceUrl(), 1))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName(
            "Given a path parameter productId invalid" +
                    "When call get api similar products" +
                    "Then return exception error code 404")
    public void givenAProductIdInValid_whenCallApi_thenReturnException404() throws Exception {
        when(mockAPIClient.getSimilarProductIds(anyString())).thenThrow(new ProductNotFoundException("Not found"));
        mockMvc.perform(get(getServiceUrl(), 1))
                .andExpect(status().isNotFound());

    }
}
