package de.hska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import de.hska.filters.pre.PreFilter;

@SpringBootApplication
@EnableZuulProxy
public class MicroserviceEshopZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEshopZuulApplication.class, args);
	}

	@Bean
	public PreFilter preFilder() {
		return new PreFilter();
	}
}
