package de.hska.client;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import de.hska.model.Role;
import de.hska.model.User;

@Component
public class UserClient {

	private final Map<Integer, User> userCache = new LinkedHashMap<Integer, User>();
	private final Map<Integer, Role> roleCache = new LinkedHashMap<Integer, Role>();

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getUsersCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public Iterable<User> getUsers() {
		Collection<User> users = new HashSet<User>();
		User[] tmpUsers = restTemplate.getForObject("http://user-service/users", User[].class);
		Collections.addAll(users, tmpUsers);
		userCache.clear();
		users.forEach(u -> userCache.put(u.getUserId(), u));
		return users;
	}

	@HystrixCommand(fallbackMethod = "getUserCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public User getUser(Integer userId) {
		User tmpUser = restTemplate.getForObject("http://user-service/users/" + userId, User.class);
		userCache.putIfAbsent(userId, tmpUser);
		return tmpUser;
	}

	@HystrixCommand(fallbackMethod = "userLoginFromCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public User userLogin(User user) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://user-service/users/login");

		User tmpUser = restTemplate.postForObject(builder.build().encode().toUri(), user, User.class);
		return tmpUser;
	}

	@HystrixCommand(fallbackMethod = "userCreateFromCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public User userCreate(User user) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://user-service/users");

		User tmpUser = restTemplate.postForObject(builder.build().encode().toUri(), user, User.class);
		return tmpUser;
	}

	@HystrixCommand(fallbackMethod = "userIsAdminFromCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public boolean userIsAdmin(Integer userId) {
		boolean isAdmin = restTemplate.getForObject("http://user-service/users/isadmin/" + userId.toString(),
				Boolean.class);
		return isAdmin;
	}

	@HystrixCommand(fallbackMethod = "getUserCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public User getUserByUsername(String username) {
		User tmpUser = restTemplate.getForObject("http://user-service/users/names/" + username, User.class);
		if (tmpUser != null) {
			userCache.putIfAbsent(tmpUser.getUserId(), tmpUser);
		}
		return tmpUser;
	}

	@HystrixCommand(fallbackMethod = "getRoleCache", commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
	public Role getRoleByLevel(Integer level) {
		Role role = restTemplate.getForObject("http://user-service/users/role/" + level, Role.class);
		if (role != null) {
			roleCache.putIfAbsent(role.getId(), role);
		}
		return role;
	}

	public Iterable<User> getUsersCache() {
		return userCache.values();
	}

	public User getUserCache(Integer userId) {
		return userCache.getOrDefault(userId, new User());
	}

	public User getUserCache(String username) {
		return null;
	}

	public User userLoginFromCache(User user) {
		// !TODO get corresponding user
		return null;
	}

	public boolean userIsAdminFromCache(Integer id) {
		// !TODO check if user is admin
		return false;
	}

	public User userCreateFromCache(User user) {
		// !TODO
		return null;
	}

	public Role getRoleCache(Integer level) {
		return roleCache.getOrDefault(level, new Role());
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
