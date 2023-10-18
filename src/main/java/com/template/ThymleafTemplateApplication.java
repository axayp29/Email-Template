package com.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class ThymleafTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymleafTemplateApplication.class, args);
	}

}