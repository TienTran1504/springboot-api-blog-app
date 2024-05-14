package com.springboot.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog REST API",
				version = "v1.0",
				description = "Spring Boot Blog REST APIs Documentation",
				contact = @Contact(
						name = "Tien Tran",
						url = "https://www.github.com/TienTran1504"
				),
				license = @License(
						name = "Apache 2.0",
						url = "http://www.javaguides.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Blog REST API Guide",
				url = "https://www.github.com/TienTran1504"
		)
)
public class SpringBootBlogRestApiApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogRestApiApplication.class, args);
	}

}
