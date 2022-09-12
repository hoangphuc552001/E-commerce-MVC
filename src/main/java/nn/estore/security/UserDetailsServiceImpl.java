package nn.estore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nn.estore.jpa.dao.UserDAO;
import nn.estore.jpa.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user = dao.findById(username).get();
			return new UserDetailsImpl(user);
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException(username + " not found!");
		}
	}

}
