package com.project.fitness.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SwaggerConfiguration
{
	
	@Bean
	public OpenAPI customeOpenAPI()
	{
		return new OpenAPI().info(new Info().title("Fitness Tracking  API")
											.version("1.0")
											.description("Production Grade  API's ")
											.contact(new Contact().email("dongaresujal@123").name("Sujal Dongare")).license(new License().name("Apache 2.0"))
													);
	}

}
