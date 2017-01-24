package de.hska;

import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@SpringBootApplication
@EnableZuulProxy
@EnableHystrixDashboard
@EnableHystrix
@EnableTurbine
public class MicroserviceEshopZuulApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEshopZuulApplication.class, args);
	}	
	
	@Component
	public class RelayTokenFilter extends ZuulFilter {
		
	    @Override
	    public String filterType() {
	        return "pre";
	    }
	 
	    @Override
	    public int filterOrder() {
	        return 10000;
	    }
	 
	    @Override
	    public boolean shouldFilter() {
	        return true;
	    }
	 
	    @Override
	    public Object run() {
	        RequestContext context = RequestContext.getCurrentContext();
	 
	        @SuppressWarnings("unchecked") Set<String> ignoredHeaders = (Set<String>) context.get("ignoredHeaders");
	        ignoredHeaders.remove("authorization");
	 
	        return null;
	    }
	}
}
