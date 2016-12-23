package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import hska.iwi.eShopMaster.model.database.dataAccessObjects.util.RestConnectionHelper;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

public class ProductDAO {
	private static final String PRODUCT_BASE_URL = "http://localhost:8081/product-api/products";

	public List<Product> getProductListByCriteria(String searchDescription, Double searchMinPrice,
			Double searchMaxPrice) {
		List<Product> products = null;
		HashMap<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("searchString", searchDescription);
		queryParams.put("minPrice", searchMinPrice);
		queryParams.put("maxPrice", searchMaxPrice);

		Response response = RestConnectionHelper.getResponseForURL(PRODUCT_BASE_URL, queryParams);
		if (response.getStatus() == 200) {
			products = response.readEntity(new GenericType<List<Product>>() {
			});
		}
		return products;
	}

	public List<Product> getProducts() {
		List<Product> products = null;
		Response response = RestConnectionHelper.getResponseForURL(PRODUCT_BASE_URL);
		if (response.getStatus() == 200) {
			products = response.readEntity(new GenericType<List<Product>>() {
			});
		}
		return products;
	}

	public Product getProductById(int id) {
		Product product = null;
		Response response = RestConnectionHelper.getResponseForURL(PRODUCT_BASE_URL + "/" + id);
		if (response.getStatus() == 200) {
			product = response.readEntity(Product.class);
		}
		return product;
	}

	public Product getProductByName(String name) {
		Product product = null;
		// TODO is this needed?
		// Response response =
		// RestConnectionHelper.getResponseForURL(PRODUCT_BASE_URL + "/" +
		// name);
		// if (response.getStatus() == 200) {
		// product = response.readEntity(Product.class);
		// }
		return product;
	}

	public void deleteById(int id, int requestingUserId) {
		Response response = RestConnectionHelper.deleteResponseForURL(PRODUCT_BASE_URL + "/" + id, requestingUserId);
		System.out.println("Response product delete: " + response.getStatus());
	}

}
