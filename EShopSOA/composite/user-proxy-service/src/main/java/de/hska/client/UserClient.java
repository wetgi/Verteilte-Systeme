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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import de.hska.model.User;

@Component
public class UserClient {

	private final Map<Integer, User> userCache = new LinkedHashMap<Integer, User>();

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

	public Iterable<User> getUsersCache() {
		return userCache.values();
	}

	public User getUserCache(Integer userId) {
		return userCache.getOrDefault(userId, new User());
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
