package hska.iwi.eShopMaster.model.database.dataAccessObjects.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hska.iwi.eShopMaster.model.database.dataobjects.User;

public class RestConnectionHelper {
	public static Response getResponseForURL(String url) {
		Builder request = getNewRequestBuilderForURL(url);
		return request.get();
	}

	public static Response postResponseForURL(String url, User user) {
		Builder request = getNewRequestBuilderForURL(url);
		return request.post(Entity.json(user));
	}

	private static Builder getNewRequestBuilderForURL(String url) {
		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target(url);

		Builder request = resource.request();
		request.accept(MediaType.APPLICATION_JSON);
		return request;
	}

}
