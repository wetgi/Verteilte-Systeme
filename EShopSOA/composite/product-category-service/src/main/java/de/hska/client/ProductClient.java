package de.hska.client;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import de.hska.model.Category;
import de.hska.model.Product;

@Component
public class ProductClient {

	private final Map<Integer, Product> productCache = new LinkedHashMap<>();
	private final Map<Integer, Category> categoryCache = new LinkedHashMap<>();

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getProductsCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public Iterable<Product> getProducts(String searchString, Double minPrice, Double maxPrice) {
		Collection<Product> products = new HashSet<Product>();
		if (searchString == null) {
			searchString = "";
		}
		if (minPrice == null) {
			minPrice = Double.MIN_VALUE;
		}
		if (maxPrice == null) {
			maxPrice = Double.MAX_VALUE;
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://product-service/products")
				.queryParam("searchString", searchString).queryParam("minPrice", minPrice)
				.queryParam("maxPrice", maxPrice);

		Product[] tmpProducts = restTemplate.getForObject(builder.build().encode().toUri(), Product[].class);
		for (Product product : tmpProducts) {
			addCategoryToProduct(product);
		}

		Collections.addAll(products, tmpProducts);
		productCache.clear();
		products.forEach(p -> productCache.put(p.getProductId(), p));
		return products;
	}

	@HystrixCommand(fallbackMethod = "getProductCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public Product getProduct(Integer productId) {
		Product tmpProduct = restTemplate.getForObject("http://product-service/products/" + productId, Product.class);
		addCategoryToProduct(tmpProduct);
		productCache.putIfAbsent(productId, tmpProduct);
		return tmpProduct;
	}

	public Iterable<Product> getProductsCache(String searchString, Double minPrice, Double maxPrice) {
		// !TODO return products in range
		return productCache.values();
	}

	public Product getProductCache(Integer productId) {
		return productCache.getOrDefault(productId, new Product());
	}

	private void addCategoryToProduct(Product product) {
		// !TODO error if no category with given id found
		Category category = this.getCategory(product.getCategoryId());
		product.setCategoryName(category.getName());
	}

	@HystrixCommand(fallbackMethod = "getCachedCategories", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public Iterable<Category> getCategories() {
		Collection<Category> categories = new HashSet<Category>();
		Category[] tmpCategories = restTemplate.getForObject("http://category-service/categories", Category[].class);
		Collections.addAll(categories, tmpCategories);
		categoryCache.clear();
		categories.forEach(c -> categoryCache.put(c.getCategoryId(), c));
		return categories;
	}

	public Iterable<Category> getCachedCategories() {
		return categoryCache.values();
	}

	@HystrixCommand(fallbackMethod = "getCachedCategory", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public Category getCategory(Integer categoryId) {
		Category category = restTemplate.getForObject("http://category-service/categories/{categoryId}", Category.class,
				categoryId);
		if (category == null) {
			return new Category();
		}
		return category;
	}

	public Category getCachedCategory(Integer categoryId) {
		return categoryCache.getOrDefault(categoryId, new Category());
	}

	public ResponseEntity<Void> deleteProduct(Integer productId) {
		return restTemplate.exchange("http://product-service/products/{productId}", HttpMethod.DELETE, null, Void.class,
				productId);
	}

	public ResponseEntity<Void> deleteCategory(Integer categoryId) {
		return restTemplate.exchange("http://category-service/categories/{categoryId}", HttpMethod.DELETE, null,
				Void.class, categoryId);
	}

	public Category createCategory(Category category) {
		return restTemplate.postForObject("http://category-service/categories", category, Category.class);
	}

	public Product createProduct(Product product) {
		Product postForObject = restTemplate.postForObject("http://product-service/products", product, Product.class);
		return postForObject;
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(HttpStatus statusCode) {
				return false;
			}
		});
		return restTemplate;
	}
}
