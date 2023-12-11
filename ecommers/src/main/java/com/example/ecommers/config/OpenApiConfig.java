package com.example.ecommers.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
/**
 * Configuration class for OpenAPI documentation using Swagger.
 * This class is annotated with {@code @OpenAPIDefinition} to define the OpenAPI specification for the application.
 *
 * @see io.swagger.v3.oas.annotations.OpenAPIDefinition
 * @see io.swagger.v3.oas.annotations.info.Info
 * @see io.swagger.v3.oas.annotations.info.Contact
 * @see io.swagger.v3.oas.annotations.servers.Server
 */
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Prest team",
                        email = "prestteam@gmail.com"
                ),
                description = "OpenApi doc prestService",
                title = "OpenApi DOC",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Local dev ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Remote dev ENV",
                        url = ""
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {


}

