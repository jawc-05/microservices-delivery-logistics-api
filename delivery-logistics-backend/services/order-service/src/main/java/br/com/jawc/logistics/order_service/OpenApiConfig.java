package br.com.jawc.logistics.order_service; // Ajuste para o pacote do seu microsserviço

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation")
                        .version("1.0.0")
                        .description("Microservices Documentation"));
    }
}