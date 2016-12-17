package de.hska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import de.hska.filters.pre.PreFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableHystrixDashboard
@EnableHystrix
@EnableTurbine
public class MicroserviceEshopZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEshopZuulApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
}
