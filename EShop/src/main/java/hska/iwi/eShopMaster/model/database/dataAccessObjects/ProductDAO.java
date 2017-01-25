package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import hska.iwi.eShopMaster.configuration.RestTemplateFactory;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

@SuppressWarnings("unchecked")
public class ProductDAO {
	private static final String PRODUCT_BASE_URL = "http://localhost:8081/product-api/products";

	public List<Product> getProductListByCriteria(String searchDescription, Double searchMinPrice,
			Double searchMaxPrice) {
		ArrayList<Product> products = new ArrayList<>();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PRODUCT_BASE_URL)
				.queryParam("searchString", searchDescription).queryParam("minPrice", searchMinPrice)
				.queryParam("maxPrice", searchMaxPrice);

		products = RestTemplateFactory.getRestTemplate().getForObject(builder.build().encode().toUri(),
				products.getClass());

		return products;
	}

	public List<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<>();
		products = RestTemplateFactory.getRestTemplate().getForObject(PRODUCT_BASE_URL, products.getClass());
		return products;
	}

	public Product getProductById(int id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PRODUCT_BASE_URL)
				.pathSegment(String.valueOf(id));
		return RestTemplateFactory.getRestTemplate().getForObject(builder.build().encode().toUri(), Product.class);
	}

	public void deleteById(int id) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PRODUCT_BASE_URL)
				.pathSegment(String.valueOf(id));
		RestTemplateFactory.getRestTemplate().exchange(builder.build().encode().toUri(), HttpMethod.DELETE, null,
				Void.class);
	}

	public void addProduct(Product product) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(PRODUCT_BASE_URL);
		RestTemplateFactory.getRestTemplate().postForObject(builder.build().encode().toUri(), product, Product.class);
	}
}
