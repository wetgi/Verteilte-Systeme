package de.hska;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import de.hska.model.User;

public class MyAuthenticationManager implements AuthenticationManager, InitializingBean{
	
	@Autowired
	private UserClient userClient;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal() + "";
	    String password = authentication.getCredentials() + "";
	    
	    System.out.println("Auth - Username: " + username);
	    System.out.println("Auth - Password: " + password);
	    
	    User user = new User();
	    user.setName("admin");
	    user.setPassword("admin");
	    		//userClient.getUser(username).getBody();
	    		//restTemplate.getForObject("http://user-api/users/names/" + username, User.class);
	    if (user == null) {
	        throw new BadCredentialsException("1000");
	    }
	    if (!password.equals(user.getPassword())) {
	        throw new BadCredentialsException("1000");
	    }
	    
	    // TODO
	    List<String> userRights = new ArrayList<>();
	    userRights.add("admin");
	    return new UsernamePasswordAuthenticationToken(username, password, userRights.stream().map(
	    		x -> new SimpleGrantedAuthority(x)).collect(Collectors.toList()));
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}

}
