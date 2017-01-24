package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import java.io.StringReader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import hska.iwi.eShopMaster.model.database.dataAccessObjects.util.RestConnectionHelper;
import hska.iwi.eShopMaster.model.database.dataobjects.Token;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

public class UserDAO {
	private static final String USER_BASE_URL = "http://localhost:8081/user-api/users";

	public User getUserByUsername(String name) {
		User user = null;
		Response response = RestConnectionHelper.getResponseForURL(USER_BASE_URL + "/names/" + name);
		if (response.getStatus() == 200) {
			user = response.readEntity(User.class);
		}
		return user;
	}

	public Token getToken(String username, String password) {
		ClientConfig clientConfig = new ClientConfig();

		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("acme", "acmesecret");
		clientConfig.register(feature);

		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://localhost:9999/uaa/oauth").path("token")
				.queryParam("grant_type", "password").queryParam("username", username).queryParam("password", password);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.json(""));

		if (response.getStatus() == 200) {
			return response.readEntity(Token.class);
		}

		return null;
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
