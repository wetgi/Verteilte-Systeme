package de.hska.client;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import de.hska.model.Product;

@Component
public class ProductClient {

	private final Map<Integer, Product> productCache = new LinkedHashMap<Integer, Product>();

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getProductsCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public Iterable<Product> getProducts() {
		Collection<Product> products = new HashSet<Product>();
		Product[] tmpProducts = restTemplate.getForObject("http://product-service/products", Product[].class);
		Collections.addAll(products, tmpProducts);
		productCache.clear();
		products.forEach(p -> productCache.put(p.getProductId(), p));
		return products;
	}

	@HystrixCommand(fallbackMethod = "getProductCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public Product getProduct(Integer productId) {
		Product tmpProduct = restTemplate.getForObject("http://product-service/products/" + productId, Product.class);
		productCache.putIfAbsent(productId, tmpProduct);
		return tmpProduct;
	}

	public Iterable<Product> getProductsCache() {
		return productCache.values();
	}

	public Product getProductCache(Integer productId) {
		return productCache.getOrDefault(productId, new Product());
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
