package de.hska.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.hska.client.UserClient;
import de.hska.model.Role;
import de.hska.model.User;
import io.swagger.annotations.ApiParam;

@RestController
@EnableCircuitBreaker
public class UserProxyServiceController {
	@Autowired
	private UserClient userClient;
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> getUsers() {
		return new ResponseEntity<Iterable<User>>(userClient.getUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(
			@ApiParam(value = "Information about the new user.", required = true) @RequestBody User newUser) {
		return new ResponseEntity<User>(userClient.userCreate(newUser), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable Integer userId) {
		return new ResponseEntity<>(userClient.getUser(userId), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@RequestMapping(value = "/users/names/{username}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable String username) {
		return new ResponseEntity<>(userClient.getUserByUsername(username), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@RequestMapping(value = "/users/role/{level}", method = RequestMethod.GET)
	public ResponseEntity<Role> getRoleByLevel(@PathVariable Integer level) {
		return new ResponseEntity<>(userClient.getRoleByLevel(level), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public ResponseEntity<User> userLogin(
			@ApiParam(value = "Information about the user.", required = true) @RequestBody User user) {
		User userLogin = userClient.userLogin(user);
		if (userLogin != null) {
			return new ResponseEntity<User>(userLogin, HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@RequestMapping(value = "/users/isadmin/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> userIsAdmin(
			@ApiParam(value = "Id of the requesting user.", required = true) @PathVariable Integer userId) {
		boolean isAdmin = userClient.userIsAdmin(userId);
		return new ResponseEntity<>(isAdmin, HttpStatus.OK);
	}
}
