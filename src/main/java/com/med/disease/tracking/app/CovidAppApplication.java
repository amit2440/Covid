package com.med.disease.tracking.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
@EnableConfigurationProperties
public class CovidAppApplication {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("ValidationMessages");
		return messageSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(CovidAppApplication.class, args);
	}
}
