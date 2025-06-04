package com.bankmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class InternateBankingApplicationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternateBankingApplicationProjectApplication.class, args);
		
		System.out.println("Internate Banking Application Start");
	}

	@Bean
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
}
