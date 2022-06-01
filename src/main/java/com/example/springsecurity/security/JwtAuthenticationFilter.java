package com.example.springsecurity.security;

import jakarta.servlet.*;

import java.io.IOException;

@SuppressWarnings("ALL")
public class JwtAuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
	}
}
