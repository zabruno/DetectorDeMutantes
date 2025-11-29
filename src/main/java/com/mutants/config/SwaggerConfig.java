package com.mutants.config;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Detector de Mutantes API")
                        .description("Proyecto final - Bruno Zaupa")
                        .version("1.0"))
                // Esto ayuda mucho cuando despliegas en la nube
                .servers(List.of(
                        new Server().url("https://detectordemutantes.onrender.com").description("Servidor Producción"),
                        new Server().url("http://localhost:8080").description("Servidor Local")
                ));
    }
}
