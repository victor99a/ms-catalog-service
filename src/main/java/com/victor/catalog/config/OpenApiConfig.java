package com.victor.catalog.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API Catálogo Pastelería Mil Sabores",
                version = "1.0",
                description = "API REST para gestionar productos del catálogo de la pastelería (ms-catalog-service)."
        ),
        servers = {
                @Server(url = "http://localhost:8081", description = "Servidor local")
        }
)
public class OpenApiConfig {

}
