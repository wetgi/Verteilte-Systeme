package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.iwi.eShopMaster.model.database.dataobjects.User;

public interface UserManager {

	public User registerUser(String username, String name, String lastname, String password);

	public User getUserByUsername(String username);

	public boolean deleteUserById(int id);

	public boolean doesUserAlreadyExist(String username);

	public void getToken(User user);
}
