package hu.cubix.hr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/*@Bean
	public UserDetailsService userDetailsService(){
		User.UserBuilder users = User.builder();
		UserDetails user = users
				.username("user")
				.password(passwordEncoder().encode("pass"))
				.authorities("user", "admin")
				.build();

		return new InMemoryUserDetailsManager(user);
	}*/

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		return http
				.httpBasic(
						Customizer.withDefaults()
				)
				.csrf(
						csrf -> csrf.disable()
				)
				.authorizeHttpRequests(auth ->
						auth
								.requestMatchers(HttpMethod.POST, "/api/login").permitAll()
								.requestMatchers( "api/timeoffrequests/**").authenticated()
								.requestMatchers("api/employees/**").authenticated()
								.requestMatchers("api/timeoffrequests/**").authenticated()
						)
				.build();
	}

}
