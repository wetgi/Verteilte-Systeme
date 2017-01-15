package de.hska;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.client.RestTemplate;

import de.hska.model.User;

public class MyAuthenticationManager implements AuthenticationManager, InitializingBean{
	
	private RestTemplate restTemplate = new RestTemplate();
    
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal() + "";
	    String password = authentication.getCredentials() + "";
	    
	    User user = restTemplate.getForObject("http://localhost:8094/users/names/" + username, User.class);
	    
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
