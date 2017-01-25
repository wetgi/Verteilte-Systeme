package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.ProductDAO;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

import java.util.List;

public class ProductManagerImpl implements ProductManager {
	private ProductDAO helper;

	public ProductManagerImpl() {
		helper = new ProductDAO();
	}

	public List<Product> getProducts() {
		return helper.getProducts();
	}

	public List<Product> getProductsForSearchValues(String searchDescription, Double searchMinPrice,
			Double searchMaxPrice) {
		return new ProductDAO().getProductListByCriteria(searchDescription, searchMinPrice, searchMaxPrice);
	}

	public Product getProductById(int id) {
		return helper.getProductById(id);
	}

	public void addProduct(String name, double price, int categoryId, String details) {
		CategoryManager categoryManager = new CategoryManagerImpl();
		Category category = categoryManager.getCategory(categoryId);

		 if(category != null){
			 Product product;
			 if(details == null){
				 product = new Product().name(name).price(price).categoryId(categoryId);
			 } else{
				 product = new Product().name(name).price(price).categoryId(categoryId).details(details);
			 }
			 helper.addProduct(product);
		 }
	}

	public void deleteProductById(int id) {
		helper.deleteById(id);
	}

}
