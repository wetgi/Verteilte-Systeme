package de.hska.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.hska.client.ProductClient;
import de.hska.model.Product;
import io.swagger.annotations.ApiParam;

@RestController
@EnableCircuitBreaker
public class ProductCategoryServiceController {
	@Autowired
	private ProductClient productClient;

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
}
