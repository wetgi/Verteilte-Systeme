package io.swagger.api;

import io.swagger.model.User;
import io.swagger.model.Error;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-08T10:50:15.291Z")

@Controller
public class UsersApiController implements UsersApi {

    public ResponseEntity<Void> usersGet() {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<User> usersPost(@ApiParam(value = "Information about the new user." ,required=true ) @RequestBody User newUser) {
        // do some magic!
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    public ResponseEntity<User> usersUserIdGet(@ApiParam(value = "Get details for user",required=true ) @PathVariable("userId") Integer userId) {
        // do some magic!
        return new ResponseEntity<User>(HttpStatus.OK);
    }

}
