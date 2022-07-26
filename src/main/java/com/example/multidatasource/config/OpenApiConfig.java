package com.example.multidatasource.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.*;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Autowired
	Environment env;

	private static final String API_KEY = "Bearer";
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("Bearer", 
								new SecurityScheme()
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")))
				.addServersItem(new Server().url("http://localhost:8510/"))
				.info(new Info().title("MultiDatasource")
						.description("MultiDatasource")
						.version(env.getProperty("info.build.version"))
						.license(new License().name("Apache 2.0").url("http://springdoc.org")).contact(
								new Contact().name("developer").email("shubhankar.sarkar@yahoo.com")))
				.security(Collections.singletonList(new SecurityRequirement().addList(API_KEY)));
	}
}