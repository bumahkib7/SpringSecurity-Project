package com.example.springsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityConfigurationApp {
	@Bean
	UserDetailsService userDetailsService();
}
