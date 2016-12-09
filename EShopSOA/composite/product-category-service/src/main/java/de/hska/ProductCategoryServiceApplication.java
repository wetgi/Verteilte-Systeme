package de.hska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductCategoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCategoryServiceApplication.class, args);
	}
}
