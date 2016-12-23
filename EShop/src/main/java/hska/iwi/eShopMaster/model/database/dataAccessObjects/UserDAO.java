package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import javax.ws.rs.core.Response;

import hska.iwi.eShopMaster.model.database.dataAccessObjects.util.RestConnectionHelper;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

public class UserDAO {

	public User getUserByUsername(String name) {
		User user = null;
		Response response = RestConnectionHelper.getResponseForURL("http://localhost:8081/user-api/users/names/" + name);
		if (response.getStatus() == 200) {
			user = response.readEntity(User.class);
		}
		System.out.println(user.getPassword());
		return user;
	}

	public void saveObject(User user) {
		// TODO Auto-generated method stub

	}
}
