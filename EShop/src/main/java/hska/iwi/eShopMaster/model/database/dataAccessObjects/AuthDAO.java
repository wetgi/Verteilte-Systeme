package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import hska.iwi.eShopMaster.configuration.RestTemplateFactory;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

public class AuthDAO {

	public void getToken(User user) {
		RestTemplateFactory.createAndGetOAuth2RestTemplate(user.getUsername(), user.getPassword());
	}

}
