package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import hska.iwi.eShopMaster.model.database.dataAccessObjects.util.RestConnectionHelper;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;

public class CategoryDAO {
	
	private static final String CATEGORY_BASE_URL = "http://localhost:8081/product-api/categories";
	
	public List<Category> getCategories() {
		List<Category> categories = null;
		Response response = RestConnectionHelper.getResponseForURL(CATEGORY_BASE_URL);
		if (response.getStatus() == 200) {
			categories = response.readEntity(new GenericType<List<Category>>() {
			});
		}else{
			System.err.println("Get Categories failed: " + response.getStatus());
		}
		return categories;
	}
	
	public Category getCategory(int categoryId) {
		Category category = null;
		Response response = RestConnectionHelper.getResponseForURL(CATEGORY_BASE_URL + "/" + categoryId);
		if (response.getStatus() == 200) {
			category = response.readEntity(new GenericType<Category>() {
			});
		}else{
			System.err.println("Get Category failed: " + response.getStatus());
		}
		return category;
	}
	
	public void addCategory(Category newCategory, int userId) {
		Response response = RestConnectionHelper.postResponseForURL(CATEGORY_BASE_URL, newCategory, userId);
		if (response.getStatus() == 200) {
			System.out.println("Added Category successfully!");
		}else{
			System.err.println("Add Category failed: " + response.getStatus());
		}
	}
	
	public void deleteCategory(int categoryId, int userId) {
		Response response = RestConnectionHelper.deleteResponseForURL(CATEGORY_BASE_URL + "/" + categoryId, userId);
		if (response.getStatus() == 200) {
			System.out.println("Deleted Category successfully!");
		}else{
			System.err.println("Delete Category failed: " + response.getStatus());
		}
	}

	
}
