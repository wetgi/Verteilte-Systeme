package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.RoleDAO;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.UserDAO;
import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.Token;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

/**
 * 
 * @author knad0001
 */

public class UserManagerImpl implements UserManager {
	UserDAO helper;
	
	public UserManagerImpl() {
		helper = new UserDAO();
	}

	
	public void registerUser(String username, String name, String lastname, String password, Role role) {
		User user = new User();
		user.setFirstname(name);
		user.setName(lastname);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		user.setLevel(role.getLevel());

		helper.saveUser(user);
	}

	
	public User getUserByUsername(String username) {
		if (username == null || username.equals("")) {
			return null;
		}
		return helper.getUserByUsername(username);
	}

	public boolean deleteUserById(int id) {
		User user = new User();
		user.setUserId(id);
		helper.deleteUser(user);
		return false;
	}

	public Role getRoleByLevel(int level) {
		RoleDAO roleHelper = new RoleDAO();
		return roleHelper.getRoleByLevel(level);
	}

	public boolean doesUserAlreadyExist(String username) {
		
    	User dbUser = this.getUserByUsername(username);
    	
    	if (dbUser != null){
    		return true;
    	}
    	else {
    		return false;
    	}
	}
	

	public boolean validate(User user) {
		if (user.getFirstname().isEmpty() || user.getPassword().isEmpty() || user.getRole() == null || user.getName() == null || user.getUsername() == null) {
			return false;
		}
		
		return true;
	}


	public Token getToken(String username, String password) {
		if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
			return null;
		}
		
		return helper.getToken(username, password);
	}

}
