package nn.estore.security;

import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import nn.estore.jpa.entity.User;

@Service("auth")
public class AuthService {
	@Autowired
	HttpServletRequest request;
	
	public boolean isAuthenticated() {
		return request.getRemoteUser() != null;
	}
	
	public boolean hasAnyRole(String...roles) {
		return this.isAuthenticated() && 
				Stream.of(roles).anyMatch(role -> request.isUserInRole(role));
	}
	
	public User getUser() {
		Authentication auth = (Authentication) request.getUserPrincipal();
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		return userDetails.getUser();
	}
	
	public void setUser(User user) {
		Authentication auth = (Authentication) request.getUserPrincipal();
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		userDetails.setUser(user);
	}
}