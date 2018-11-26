package com.example.projects.stockdbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@EnableJpaRepositories(basePackages = "com.example.projects.stockdbservice.repository")
@SpringBootApplication
public class StockDbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockDbServiceApplication.class, args);
	}
}
