package nn.estore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors().disable();
		
		http.authorizeRequests()
			.antMatchers("/admin/category/delete", "/admin/user/**").hasRole("ADMIN")
			.antMatchers("/admin/report/**").hasRole("SUPER")
			.antMatchers("/admin/**").hasAnyRole("STAFF", "SUPER", "ADMIN")
			.antMatchers("/order/**", 
					"/account/change-password",
					"/account/edit-profile").authenticated()
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/security/login/form")
			.loginProcessingUrl("/spring/login")
			.defaultSuccessUrl("/security/login/success")
			.failureUrl("/security/login/failure");
		http.rememberMe().tokenValiditySeconds(30*24*60*60);
		http.logout()
			.logoutUrl("/spring/logout")
			.logoutSuccessUrl("/security/logout/success")
			.addLogoutHandler(new SecurityContextLogoutHandler());
		http.exceptionHandling()
			.accessDeniedPage("/security/access/denied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/api/**");
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}