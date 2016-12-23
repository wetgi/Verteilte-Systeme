package de.hska.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hska.model.Role;
import de.hska.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-08T10:50:15.291Z")

@Api(value = "users", description = "the users API")
public interface UsersApi {

	@ApiOperation(value = "Retrieves all users", notes = "", response = User.class, authorizations = {
			@Authorization(value = "UserSecurity") }, tags = { "user", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = User.class),
			@ApiResponse(code = 401, message = "Failed", response = User.class) })
	@RequestMapping(value = "/users", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<User>> usersGet();

	@ApiOperation(value = "Creates a new user.", notes = "", response = User.class, tags = { "user", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Created the user.", response = User.class),
			@ApiResponse(code = 405, message = "User already in system.", response = User.class) })
	@RequestMapping(value = "/users", produces = { "application/json" }, method = RequestMethod.POST)
	ResponseEntity<User> usersPost(
			@ApiParam(value = "Information about the new user.", required = true) @RequestBody User newUser);

	@ApiOperation(value = "Get details for a certain user.", notes = "", response = User.class, authorizations = {
			@Authorization(value = "UserSecurity") }, tags = { "user", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Returns the user", response = User.class),
			@ApiResponse(code = 404, message = "User not found", response = User.class) })
	@RequestMapping(value = "/users/names/{username}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<User> usersUserUsernameGet(
			@ApiParam(value = "Get details for user", required = true) @PathVariable("username") String username);

	@ApiOperation(value = "Get role information by level", notes = "", response = Role.class, authorizations = {
			@Authorization(value = "UserSecurity") }, tags = { "user", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Returns the role", response = Role.class),
			@ApiResponse(code = 404, message = "Role not found", response = Role.class) })
	@RequestMapping(value = "/users/role/{level}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Role> usersUserRoleGet(
			@ApiParam(value = "Level of role", required = true) @PathVariable("level") Integer level);

	@ApiOperation(value = "Get details for a certain user.", notes = "", response = User.class, authorizations = {
			@Authorization(value = "UserSecurity") }, tags = { "user", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Returns the user", response = User.class),
			@ApiResponse(code = 404, message = "User not found", response = User.class) })
	@RequestMapping(value = "/users/{userId}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<User> usersUserIdGet(
			@ApiParam(value = "Get details for user", required = true) @PathVariable("userId") Integer userId);

	@ApiOperation(value = "Login user", notes = "", response = User.class, tags = { "user", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Created the user.", response = User.class),
			@ApiResponse(code = 204, message = "Wrong credentials", response = User.class) })
	@RequestMapping(value = "/users/login", produces = { "application/json" }, method = RequestMethod.POST)
	ResponseEntity<User> userLoginDataValid(
			@ApiParam(value = "Information about the user.", required = true) @RequestBody User user);

	@ApiOperation(value = "Check if requesting user is admin", notes = "", response = Boolean.class, tags = { "user", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Admin", response = Boolean.class),
			@ApiResponse(code = 204, message = "No admin", response = Boolean.class) })
	@RequestMapping(value = "/users/isadmin/{userId}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Boolean> userIsAdmin(
			@ApiParam(value = "Id of the requesting user.", required = true) @PathVariable("userId") Integer userId);

	@ApiOperation(value = "Delete a certain user.", notes = "", response = Void.class, authorizations = {
			@Authorization(value = "AdminSecurity") }, tags = { "user", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Void.class),
			@ApiResponse(code = 404, message = "User not found", response = Void.class) })
	@RequestMapping(value = "/users/{userId}", produces = { "application/json" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> productsProductIdDelete(
			@ApiParam(value = "Id of user to delete", required = true) @PathVariable("userId") Integer userId,
			@ApiParam(value = "The requesting user", required = true) @RequestHeader(value = "userId", required = true) Integer requestUserId);
}
