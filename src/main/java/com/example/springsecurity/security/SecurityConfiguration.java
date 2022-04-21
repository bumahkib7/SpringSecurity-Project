package com.example.springsecurity.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.springsecurity.security.ApplicationUserRole.*;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.core.userdetails.User.*;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration implements SecurityConfigurationApp {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SecurityConfiguration(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authz) -> authz
						.antMatchers("/", "/index", "/css/*", "/js/*")
						.permitAll()
						.antMatchers("/api/**").hasRole(STUDENT.name())
						.anyRequest()
						.authenticated()
				)
				.formLogin(withDefaults())
				.httpBasic()
				.and()
				.csrf()
				.disable();
		return http.build();

	}


	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails janeDoeUser = builder()
				.username("JaneDoe")
				.password(passwordEncoder.encode("password"))
				.roles(STUDENT.name())
				.build();

		UserDetails johnDoeUser = builder()
				.username("john")
				.password(passwordEncoder.encode("password123"))
				.roles(ADMIN.name())
				.build();

		return new InMemoryUserDetailsManager(janeDoeUser
				,johnDoeUser
		);
	}
}

