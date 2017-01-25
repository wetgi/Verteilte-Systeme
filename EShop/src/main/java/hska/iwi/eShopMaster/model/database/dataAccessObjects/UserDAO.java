package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;

import hska.iwi.eShopMaster.configuration.RestTemplateFactory;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.util.RestConnectionHelper;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

public class UserDAO {
	private static final String USER_BASE_URL = "http://localhost:8081/user-api/users";

	public User getUserByUsername(String name) {
		ResponseEntity<User> response = null;
		try {
			response = RestTemplateFactory.getRestTemplate().getForEntity(USER_BASE_URL + "/names/" + name, User.class);
		} catch (OAuth2AccessDeniedException e) {
			if(e.getCause().getMessage().equals("999")) {
				// return a null user => username not found
				return null;
			} else if(e.getCause().getMessage().equals("1001")) {
				// return a dummy user => password wrong
				User dummy = new User();
				return dummy;
			} 
		}
		return response.getBody();
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
