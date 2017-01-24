package de.hska;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrix
@RibbonClient("user-proxy-service")
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserProxyServiceApplication extends ResourceServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(UserProxyServiceApplication.class, args);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
	   http.authorizeRequests().antMatchers(HttpMethod.POST, "/users").permitAll();
	   
	}
}
