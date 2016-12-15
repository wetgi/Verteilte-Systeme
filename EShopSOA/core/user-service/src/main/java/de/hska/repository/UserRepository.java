package de.hska.repository;

import org.springframework.data.repository.CrudRepository;

import de.hska.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByUsername(String username);
	
	public User findByUsernameAndPassword(String username, String password);
}
