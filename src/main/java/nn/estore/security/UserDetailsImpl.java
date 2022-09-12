package nn.estore.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import nn.estore.jpa.entity.User;
import nn.estore.jpa.entity.UserRole;

@SuppressWarnings("serial")
@Data
public class UserDetailsImpl implements UserDetails{
	private User user;

	public UserDetailsImpl(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<UserRole> userRoles = user.getUserRoles();
		if(userRoles == null || userRoles.isEmpty()) {
			return List.of();
		}
		// Chuyển đổi UserRole thành GrantedAuthority
		return userRoles.stream()
				.map(ur -> "ROLE_" + ur.getRole().getId())
				.map(au -> new SimpleGrantedAuthority(au)).toList();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}