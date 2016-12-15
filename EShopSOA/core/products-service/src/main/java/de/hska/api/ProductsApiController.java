package de.hska.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import de.hska.model.Product;
import de.hska.repository.ProductRepository;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-08T10:50:15.291Z")

@Controller
public class ProductsApiController implements ProductsApi {

	@Autowired
	private ProductRepository pr;

	public ResponseEntity<List<Product>> productsGet(
			@ApiParam(value = "Is contained in product name?") @RequestParam(value = "searchString", required = false) String searchString,
			@ApiParam(value = "Does product cost at least x?") @RequestParam(value = "minPrice", required = false) Double minPrice,
			@ApiParam(value = "Does product cost at max x?") @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
		if (searchString == null) {
			searchString = "";
		}
		if (minPrice == null) {
			minPrice = Double.MIN_VALUE;
		}
		if (maxPrice == null) {
			maxPrice = Double.MAX_VALUE;
		}
		List<Product> products = pr.findByNameContainingAndPriceBetween(searchString, minPrice, maxPrice);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	public ResponseEntity<Product> productsPost(
			@ApiParam(value = "Information about the new product.", required = true) @RequestBody Product newProduct,
			@ApiParam(value = "The requesting user", required = true) @RequestHeader(value = "userId", required = true) Integer userId) {
		// !TODO check if user is eligible
		// return new ResponseEntity<Product>(HttpStatus.METHOD_NOT_ALLOWED);
		// !TODO check if new product is valid?
		pr.save(newProduct);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}

	public ResponseEntity<Void> productsProductIdDelete(
			@ApiParam(value = "Deletes a product", required = true) @PathVariable("productId") Integer productId,
			@ApiParam(value = "The requesting user", required = true) @RequestHeader(value = "userId", required = true) Integer userId) {
		// !TODO check if user is eligible
		// return new ResponseEntity<Product>(HttpStatus.METHOD_NOT_ALLOWED);
		if (pr.exists(productId)) {
			pr.delete(productId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Product> productsProductIdGet(
			@ApiParam(value = "Get details for product", required = true) @PathVariable("productId") Integer productId) {
		Product product = pr.findOne(productId);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

}
