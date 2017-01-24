package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;

import hska.iwi.eShopMaster.configuration.RestTemplateFactory;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.util.RestConnectionHelper;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

public class UserDAO {
	private static final String USER_BASE_URL = "http://localhost:8081/user-api/users";

	public User getUserByUsername(String name) {
		ResponseEntity<User> response = RestTemplateFactory.getRestTemplate()
				.getForEntity(USER_BASE_URL + "/names/" + name, User.class);
		User user = response.getBody();
		return user;
	}

	public void saveUser(User user) {
		Response response = RestConnectionHelper.postResponseForURL(USER_BASE_URL, user);
		System.out.println("Register user code: " + response.getStatus());
	}

	public void deleteUser(User user) {
		// TODO is this needed?
		System.err.println("");
	}
}
