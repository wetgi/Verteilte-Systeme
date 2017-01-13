package de.hska;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hska.model.User;

@FeignClient("user-service")
public interface UserClient {
	
	@RequestMapping(value = "/users/names/{username}", method = RequestMethod.GET)
	ResponseEntity<User> getUser(@PathVariable String username);
}
