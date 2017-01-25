package de.hska.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.google.common.collect.Lists;

import de.hska.model.Role;
import de.hska.model.User;
import de.hska.repository.RoleRepository;
import de.hska.repository.UserRepository;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-08T10:50:15.291Z")

@Controller
public class UsersApiController implements UsersApi {
	private static int ADMIN_ROLE = 0;

	@Autowired
	private UserRepository ur;

	@Autowired
	private RoleRepository rr;

	public ResponseEntity<List<User>> usersGet() {
		ArrayList<User> users = Lists.newArrayList(ur.findAll());
		for (User user : users) {
			user.setRole(usersUserRoleGet(user.getLevel()).getBody());
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	public ResponseEntity<User> usersUserIdGet(
			@ApiParam(value = "Get details for user", required = true) @PathVariable("userId") Integer userId) {
		User user = ur.findOne(userId);
		if (user != null) {
			user.setRole(usersUserRoleGet(user.getLevel()).getBody());
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<User> usersPost(
			@ApiParam(value = "Information about the new user.", required = true) @RequestBody User newUser) {
		// !TODO check if user is eligible
		// return new ResponseEntity<Product>(HttpStatus.METHOD_NOT_ALLOWED);
		// !TODO check if new user is valid?
		String username = newUser.getUsername();
		if (ur.findByUsername(username) == null) {
			ur.save(newUser);
			newUser.setRole(usersUserRoleGet(newUser.getLevel()).getBody());
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
	}

	public ResponseEntity<User> userLoginDataValid(
			@ApiParam(value = "Information about the user.", required = true) @RequestBody User user) {
		User foundUser = ur.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (foundUser != null) {
			foundUser.setRole(usersUserRoleGet(foundUser.getLevel()).getBody());
			return new ResponseEntity<>(foundUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<Boolean> userIsAdmin(
			@ApiParam(value = "Id of the requesting user.", required = true) @PathVariable("userId") Integer userId) {
		if (ur.exists(userId)) {
			User user = ur.findOne(userId);
			return new ResponseEntity<>(user.getRole().getLevel() == ADMIN_ROLE, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<User> usersUserUsernameGet(
			@ApiParam(value = "Get details for user", required = true) @PathVariable("username") String username) {
		User user = ur.findByUsername(username);
		if (user != null) {
			user.setRole(usersUserRoleGet(user.getLevel()).getBody());
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<Role> usersUserRoleGet(
			@ApiParam(value = "Level of role", required = true) @PathVariable("level") Integer level) {
		Role role = rr.findByLevel(level);
		if (role != null) {
			return new ResponseEntity<Role>(role, HttpStatus.OK);
		}
		return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
	}

	public ResponseEntity<Void> productsProductIdDelete(
			@ApiParam(value = "Id of user to delete", required = true) @PathVariable("userId") Integer userId,
			@ApiParam(value = "The requesting user", required = true) @RequestHeader(value = "userId", required = true) Integer requestUserId) {
		if (ur.exists(userId)) {
			ur.delete(userId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
