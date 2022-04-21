package com.example.springsecurity.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityConfigurationBuilder {
	private PasswordEncoder passwordEncoder;

	public SecurityConfigurationBuilder setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		return this;
	}

	public SecurityConfiguration createSecurityConfiguration() {
		return new SecurityConfiguration(passwordEncoder);
	}
}