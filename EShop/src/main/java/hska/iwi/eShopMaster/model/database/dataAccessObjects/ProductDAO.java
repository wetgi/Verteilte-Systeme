package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import hska.iwi.eShopMaster.model.database.dataAccessObjects.util.RestConnectionHelper;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

public class ProductDAO {
	private static final String PRODUCT_BASE_URL = "http://localhost:8081/product-api/products";

	public List<Product> getProductListByCriteria(String searchDescription, Double searchMinPrice,
			Double searchMaxPrice) {

		// Session session =
		// HibernateUtil.getSessionFactory().getCurrentSession();
		// Transaction transaction = null;
		// List<Product> productList = null;
		//
		// try {
		// transaction = session.beginTransaction();
		// Criteria crit = session.createCriteria(Product.class);
		//
		// // Define Search HQL:
		// if (searchDescription != null && searchDescription.length() > 0)
		// { // searchValue is set:
		// searchDescription = "%"+searchDescription+"%";
		// crit.add(Restrictions.ilike("details", searchDescription ));
		// }
		//
		// if (( searchMinPrice != null) && ( searchMaxPrice != null)) {
		// crit.add(Restrictions.between("price", searchMinPrice,
		// searchMaxPrice));
		// }
		// else if( searchMinPrice != null) {
		// crit.add(Restrictions.ge("price", searchMinPrice));
		// }
		// else if ( searchMaxPrice != null) {
		// crit.add(Restrictions.le("price", searchMaxPrice));
		// }
		//
		// productList = crit.list();
		//
		// transaction.commit();
		//
		// } catch (Exception e) {
		// transaction.rollback();
		// e.printStackTrace();
		// }
		// return productList;
		return new ArrayList<Product>();
	}

	public List<Product> getProducts() {
		List<Product> products = null;
		Response response = RestConnectionHelper.getResponseForURL(PRODUCT_BASE_URL);
		if (response.getStatus() == 200) {
			products = response.readEntity(new GenericType<List<Product>>(){});
		}
		return products;
	}

}
