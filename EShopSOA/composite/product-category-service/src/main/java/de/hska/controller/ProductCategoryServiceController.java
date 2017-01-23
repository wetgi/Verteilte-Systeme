package de.hska.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.hska.client.ProductClient;
import de.hska.model.Category;
import de.hska.model.Product;
import io.swagger.annotations.ApiParam;

@RestController
@EnableCircuitBreaker
public class ProductCategoryServiceController {
	@Autowired
	private ProductClient productClient;
	
	// TODO
	//@PreAuthorize("#oauth2.hasScope('webshop') and hasRole('XXX')")
	//@PreAuthorize("hasPermission('XXX')")
	//@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Product>> getProducts(
			@ApiParam(value = "Is contained in product name?") @RequestParam(value = "searchString", required = false) String searchString,
			@ApiParam(value = "Does product cost at least x?") @RequestParam(value = "minPrice", required = false) Double minPrice,
			@ApiParam(value = "Does product cost at max x?") @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
		
		return new ResponseEntity<Iterable<Product>>(productClient.getProducts(searchString, minPrice, maxPrice),
				HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
		return new ResponseEntity<>(productClient.getProduct(productId), HttpStatus.OK);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Category>> getCategories() {
		return new ResponseEntity<Iterable<Category>>(productClient.getCategories(), HttpStatus.OK);
	}

	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<Category> getCategory(@PathVariable Integer categoryId) {
		Category category = productClient.getCategory(categoryId);
		return category.getCategoryId() != null ? new ResponseEntity<>(category, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/products/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@RequestHeader(value = "userId", required = true) String userId,
			@PathVariable Integer productId) {
		return productClient.deleteProduct(userId, productId);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCategory(@RequestHeader(value = "userId", required = true) String userId,
			@PathVariable Integer categoryId) {
		// !TODO faster way to get all products with given category?
		Iterable<Product> products = productClient.getProducts(null, null, null);

		for (Product product : products) {
			if (product.getCategoryId().equals(categoryId)) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}

		return productClient.deleteCategory(userId, categoryId);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/categories", method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<Category> createCategory(@RequestHeader(value = "userId", required = true) String userId,
			@RequestBody Category category) {
		return new ResponseEntity<Category>(productClient.createCategory(userId, category), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestHeader(value = "userId", required = true) String userId,
			@RequestBody Product product) {
		return new ResponseEntity<>(productClient.createProduct(userId, product), HttpStatus.OK);
	}

}
