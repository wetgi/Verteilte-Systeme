package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.CategoryDAO;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;

import java.util.List;

public class CategoryManagerImpl implements CategoryManager {
	private CategoryDAO helper;

	public CategoryManagerImpl() {
		helper = new CategoryDAO();
	}

	public List<Category> getCategories() {
		return helper.getCategories();
	}

	public Category getCategory(int categoryId) {
		 return helper.getCategory(categoryId);
	}

	public void addCategory(String name, int userId) {
		 Category newCategory = new Category(name);
		 helper.addCategory(newCategory, userId);
	}

	public void delCategoryById(int categoryId, int userId) {
		 helper.deleteCategory(categoryId, userId);
	}
}
