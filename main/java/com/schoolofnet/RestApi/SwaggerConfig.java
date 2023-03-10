package com.schoolofnet.RestApi;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiConfigDocs() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.schoolofnet.RestAPI.resources"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo infoDocs() {
        return new ApiInfo("Title - Rest API", "My Description", "1.0", "Terms", new Contact("Leonan", "https://schoolofnet.com" , "leonan@gmail.com"), "Apache License", "Url", new ArrayList<VendorExtension>());
    }
}