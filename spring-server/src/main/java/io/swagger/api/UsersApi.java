package io.swagger.api;

import io.swagger.model.User;
import io.swagger.model.Error;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-08T10:50:15.291Z")

@Api(value = "users", description = "the users API")
public interface UsersApi {

    @ApiOperation(value = "Retrieves all users", notes = "", response = Void.class, authorizations = {
        @Authorization(value = "UserSecurity")
    }, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success", response = Void.class),
        @ApiResponse(code = 401, message = "Failed", response = Void.class) })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Void> usersGet();


    @ApiOperation(value = "Creates a new user.", notes = "", response = User.class, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Created the user.", response = User.class),
        @ApiResponse(code = 405, message = "User already in system.", response = User.class) })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<User> usersPost(@ApiParam(value = "Information about the new user." ,required=true ) @RequestBody User newUser);


    @ApiOperation(value = "Get details for a certain user.", notes = "", response = User.class, authorizations = {
        @Authorization(value = "UserSecurity")
    }, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Returns the user", response = User.class),
        @ApiResponse(code = 404, message = "User not found", response = User.class) })
    @RequestMapping(value = "/users/{userId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<User> usersUserIdGet(@ApiParam(value = "Get details for user",required=true ) @PathVariable("userId") Integer userId);

}
