package com.inditex.products.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

        @OpenAPIDefinition(
                info = @Info(
                        title = "Similar products API",
                        version = "1.0.0",
                        description = "This API exposes operation of similar products to a given one ordered by similarity",
                        contact = @Contact(
                                name = "Daniel Rodriguez",
                                email = "jdanielrodriguezo@gmail.com"
                        )

                ),
                tags = {
                        @Tag(
                                name = "get-product-similar",
                                description = "Get information about similar products"
                        )
                },
                servers = {
                        @Server(
                                url = "http://localhost:5000", description = "Server API"
                        )
                }
        )
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenApiDefinition {
}
