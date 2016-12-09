package de.hska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MicroserviceEshopZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEshopZuulApplication.class, args);
	}
}
