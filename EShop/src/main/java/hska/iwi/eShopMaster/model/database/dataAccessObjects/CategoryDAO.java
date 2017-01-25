package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import hska.iwi.eShopMaster.configuration.RestTemplateFactory;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;

@SuppressWarnings("unchecked")
public class CategoryDAO {

	private static final String CATEGORY_BASE_URL = "http://localhost:8081/product-api/categories";

	public List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();

		categories = RestTemplateFactory.getRestTemplate().getForObject(CATEGORY_BASE_URL, categories.getClass());

		return categories;
	}

	public Category getCategory(int categoryId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CATEGORY_BASE_URL)
				.pathSegment(String.valueOf(categoryId));
		return RestTemplateFactory.getRestTemplate().getForObject(builder.build().encode().toUri(), Category.class);
	}

	public void addCategory(Category category) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CATEGORY_BASE_URL);
		RestTemplateFactory.getRestTemplate().postForObject(builder.build().encode().toUri(), category, Category.class);
	}

	public void deleteCategory(int categoryId) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(CATEGORY_BASE_URL)
				.pathSegment(String.valueOf(categoryId));

		RestTemplateFactory.getRestTemplate().exchange(builder.build().encode().toUri(), HttpMethod.DELETE, null,
					Void.class);
	}
}
