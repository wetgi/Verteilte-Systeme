package hska.iwi.eShopMaster.model.database.dataAccessObjects.util;

import java.util.HashMap;

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
	
	public static Response getResponseForURL(String url, HashMap<String, Object> queryParams) {
		Builder request = getNewRequestBuilderForURL(url, queryParams);
		return request.get();
	}

	public static Response postResponseForURL(String url, User user) {
		Builder request = getNewRequestBuilderForURL(url);
		return request.post(Entity.json(user));
	}
	


	public static Response deleteResponseForURL(String url, User user) {
		Builder request = getNewRequestBuilderForURL(url);
		return request.delete();
	}

	public static Response deleteResponseForURL(String url, Integer requestingUserId) {
		Builder request = getNewRequestBuilderForURL(url, requestingUserId);
		return request.delete();
	}

	private static Builder getNewRequestBuilderForURL(String url) {
		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target(url);

		Builder request = resource.request();
		request.accept(MediaType.APPLICATION_JSON);
		return request;
	}

	private static Builder getNewRequestBuilderForURL(String url, int requestingUserId) {
		return getNewRequestBuilderForURL(url).header("userId", requestingUserId);
	}

	private static Builder getNewRequestBuilderForURL(String url, HashMap<String, Object> queryParams) {
		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target(url);

		for (String key : queryParams.keySet()) {
			resource = resource.queryParam(key, queryParams.get(key));
		}
		Builder request = resource.request();
		request.accept(MediaType.APPLICATION_JSON);
		return request;
	}

}
