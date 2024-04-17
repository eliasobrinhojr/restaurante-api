package com.eliasjr.mbdata.restauranteapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("elias.dvlpr@gmail.com");
        contact.setName("EliasJr");
        contact.setUrl("https://www.linkedin.com/in/elias-ara%C3%BAjo-20b677a5/");

        return new OpenAPI().info(new Info()
                .title("Tutorial Management API")
                .version("1.0")
                .contact(contact)
                .description("Endpoints de gerenciamento de pedidos para um restaurante..").termsOfService("https://www.google.com/terms")
                .license(new License().name("Apache 2.5").url("http://springdoc.org")));
    }

}
