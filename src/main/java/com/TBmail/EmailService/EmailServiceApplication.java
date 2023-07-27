package com.TBmail.EmailService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "TB mail application", version = "1.0", description = "TB mail data crud"))
public class EmailServiceApplication  {
	
	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication .class, args);
				
	}
	
}
