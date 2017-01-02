package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.iwi.eShopMaster.model.database.dataobjects.Category;

import java.util.List;

public interface CategoryManager {

	public List<Category> getCategories();
	
	public Category getCategory(int id);
	
	public Category getCategoryByName(String name);
	
	public void addCategory(String name, int userId);
	
	public void delCategoryById(int categoryId, int userId);

	
}
