package csw.t1.csw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Venda de Ingressos")
                        .version("v1")
                        .description("Trabalho CSW - Grupo H")
                        .termsOfService("https://www.google.com/")
                        .license(new License().name("Apache 2.0")
                                .url("https://www.google.com/")));
    }
}
