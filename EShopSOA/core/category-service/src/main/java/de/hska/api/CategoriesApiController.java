package de.hska.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.common.collect.Lists;

import de.hska.model.Category;
import de.hska.repository.CategoryRepository;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-08T10:50:15.291Z")

@Controller
public class CategoriesApiController implements CategoriesApi {
	@Autowired
	private CategoryRepository cr;

	public ResponseEntity<List<Category>> categoriesGet() {
		return new ResponseEntity<List<Category>>(Lists.newArrayList(cr.findAll()), HttpStatus.OK);
	}

	public ResponseEntity<Category> categoriesCategoryIdGet(
			@ApiParam(value = "Get details for category", required = true) @PathVariable("categoryId") Integer categoryId) {
		Category category = cr.findOne(categoryId);
		if (category != null) {
			return new ResponseEntity<Category>(category, HttpStatus.OK);
		}
		return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Category> categoriesPost(
			@ApiParam(value = "Information about the new category.", required = true) @RequestBody Category newCategory) {
		cr.save(newCategory);
		return new ResponseEntity<Category>(newCategory, HttpStatus.OK);
	}

	public ResponseEntity<Void> categoriesCategoryIdDelete(
			@ApiParam(value = "Deletes a category", required = true) @PathVariable("categoryId") Integer categoryId) {
		if (cr.exists(categoryId)) {
			cr.delete(categoryId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
