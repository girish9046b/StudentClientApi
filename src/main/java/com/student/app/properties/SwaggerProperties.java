package com.student.app.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class SwaggerProperties {

	@Value("${spring.application.name}")
	public String title;

	@Value("${spring.application.version}")
	public String version;

	@Value("${spring.application.description}")
	public String description;

}