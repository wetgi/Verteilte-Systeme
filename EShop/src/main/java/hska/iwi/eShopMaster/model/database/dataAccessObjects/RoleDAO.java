package hska.iwi.eShopMaster.model.database.dataAccessObjects;

import javax.ws.rs.core.Response;

import hska.iwi.eShopMaster.model.database.dataAccessObjects.util.RestConnectionHelper;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;

public class RoleDAO {

	public Role getRoleByLevel(int roleLevel) {
		Role role = null;
		Response response = RestConnectionHelper.getResponseForURL("http://localhost:8081/user-api/users/role/" + roleLevel);
		if (response.getStatus() == 200) {
			role = response.readEntity(Role.class);
		}
		return role;
	}

}
