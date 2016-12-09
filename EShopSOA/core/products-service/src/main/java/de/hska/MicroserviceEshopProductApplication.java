package de.hska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceEshopProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEshopProductApplication.class, args);
	}
}
