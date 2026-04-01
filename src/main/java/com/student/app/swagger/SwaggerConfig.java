package com.student.app.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.student.app.properties.SwaggerProperties;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {


	private final SwaggerProperties swaggerProperties;

	SwaggerConfig(SwaggerProperties appProperties) {
		this.swaggerProperties = appProperties;
	}

	@Bean
    public OpenAPI SwaggerConfig() {
        return new OpenAPI()
            .info(new Info()
                .title(swaggerProperties.getTitle())
                .version(swaggerProperties.getVersion())
                .description(swaggerProperties.getDescription()));
    }
}
