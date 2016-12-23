package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import javax.ws.rs.core.Response;

import hska.iwi.eShopMaster.model.database.dataAccessObjects.util.RestConnectionHelper;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

public class UserDAO {
	private static final String USER_BASE_URL = "http://localhost:8081/user-api/users";
	
	
	public User getUserByUsername(String name) {
		User user = null;
		Response response = RestConnectionHelper
				.getResponseForURL(USER_BASE_URL+"/names/" + name);
		if (response.getStatus() == 200) {
			user = response.readEntity(User.class);
		}
		return user;
	}

	public void saveUser(User user) {
		Response response = RestConnectionHelper
				.postResponseForURL(USER_BASE_URL, user);
		System.out.println(response.getStatus());
	}
}
