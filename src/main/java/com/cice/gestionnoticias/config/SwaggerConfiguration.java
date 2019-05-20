package com.cice.gestionnoticias.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket configApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(createApiInfo());
    }

    private ApiInfo createApiInfo(){
        return new ApiInfo("API Gestion Noticias",
                "CRUD para noticias en nuestro proyecto CICE",
                "v1", "Terms of service",
                new Contact("Gustavo","https://ciceonline.com", "gustavo.gamboa@ciceonline.com"),
                "FREE",
                "API License URL", Collections.emptyList());
    }
}
