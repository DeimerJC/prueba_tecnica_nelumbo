package com.ptn.prueba_tecnica_nelumbo.infrastructure.documentation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;

@Configuration
@SecurityScheme(name = "bearerAuth", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi(@Value("${appdescription}") String appDescription,
                                 @Value("${appversion}") String appVersion){

        Schema<?> mapErrorSchema = new Schema<Map<String, String>>()
                .addProperty("message", new StringSchema().example("string"));
        Schema<?> mapMessageSchema = new Schema<Map<String, String>>()
        		.addProperty("message", new StringSchema().example("string"));
        
        return new OpenAPI()
            .components(new Components())
            .info(new Info()
                .title("Hexagonal microservice-gp")
                .version(appVersion)
                .description(appDescription)
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .components(new Components()
                .addSchemas("Error", mapErrorSchema)
                .addSchemas("Message", mapMessageSchema)
            );
    }
}