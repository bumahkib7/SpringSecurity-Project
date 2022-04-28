package com.example.springsecurity.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.springsecurity.security.ApplicationUserRole.*;
import static org.springframework.security.core.userdetails.User.builder;
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
				.csrf().disable()
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				//.and()
				.authorizeHttpRequests((authz) -> authz
						.antMatchers("/", "/index", "/css/*", "/js/*")
						.permitAll()
						.antMatchers("/management/api/**").hasRole(ADMIN.name())
						.anyRequest()
						.authenticated()

				)

				.formLogin();


		return http.build();


	}




	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails janeDoeUser = builder()
				.username("Jane")
				.password(passwordEncoder.encode("password123"))
				.roles(STUDENT.name())
				.authorities(STUDENT.getGrantedAuthorities())
				.build();

		UserDetails johnDoeUser = builder()
				.username("john")
				.password(passwordEncoder.encode("password123"))
				.roles(ADMIN.name())
				.authorities(ADMIN.getGrantedAuthorities())
				.build();


		UserDetails  tomUser = builder()
				.username("tom")
				.password(passwordEncoder.encode("password123"))
				.roles(ADMINTRAINEE.name())
				.authorities(ADMINTRAINEE.getGrantedAuthorities())
				.build();



		return new InMemoryUserDetailsManager(
				janeDoeUser,
				johnDoeUser,
				tomUser
		);
	}
}

