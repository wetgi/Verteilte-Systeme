package hska.iwi.eShopMaster.model.database.dataAccessObjects.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestConnectionHelper {
	public static Response getResponseForURL(String url) {
		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target(url);

		Builder request = resource.request();
		request.accept(MediaType.APPLICATION_JSON);

		Response response = request.get();
		return response;
	}

}
