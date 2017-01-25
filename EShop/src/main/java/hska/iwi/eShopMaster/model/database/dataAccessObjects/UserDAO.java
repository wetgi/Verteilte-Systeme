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

		// we have no token for this user
		if (RestTemplateFactory.getRestTemplate() == null) {
			return getUserByUsernameWithoutToken(name);
		}

		try {
			response = RestTemplateFactory.getRestTemplate().getForEntity(USER_BASE_URL + "/names/" + name, User.class);
		} catch (OAuth2AccessDeniedException e) {
			if (e.getCause().getMessage().equals("999")) {
				// return a null user => username not found
				return null;
			} else if (e.getCause().getMessage().equals("1001")) {
				// return a dummy user => password wrong
				User dummy = new User();
				return dummy;
			}
		}
		return response.getBody();
	}

	private User getUserByUsernameWithoutToken(String name) {
		User user = null;
		Response response = RestConnectionHelper.getResponseForURL(USER_BASE_URL + "/names/" + name);
		if (response.getStatus() == 200) {
			user = response.readEntity(User.class);
		}
		return user;
	}

	public User saveUser(User user) {
		Response response = RestConnectionHelper.postResponseForURL(USER_BASE_URL, user);
		if (response.getStatus() == 200) {
			User user2 = response.readEntity(User.class);
			return user2;
		}
		System.out.println("Register user code: " + response.getStatus());
		return null;
	}

	public void deleteUser(User user) {
		// TODO is this needed?
		System.err.println("");
	}
}
