package br.com.transaction.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI2() {

        Info infoProject = new Info()
                .title("API de clientes para exemplo")
                .description("Serviço de exemplo para gerenciar transacoes")
                .version("0.0.1");

        return new OpenAPI()
                .info(infoProject)
                .addTagsItem(new Tag().name("Documentacao Transacoes"));
    }

}
