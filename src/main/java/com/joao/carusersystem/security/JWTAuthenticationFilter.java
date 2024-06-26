package com.joao.carusersystem.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joao.carusersystem.dtos.CredentialsDTO;
import com.joao.carusersystem.models.User;
import com.joao.carusersystem.repositories.UserRepository;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private UserRepository repository;

	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserRepository repository) {
		super();
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.repository = repository;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CredentialsDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					creds.getLogin(), creds.getPassword(), new ArrayList<>());
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			return authentication;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String username = ((UserSS) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		//res.setHeader("Access-Control-Allow-Origin", "*");
		//res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		//res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, enctype, Location");
		//res.setHeader("Authorization", "Bearer " + token);
		res.setHeader("access-control-expose-headers", "Authorization");
		res.setHeader("Authorization", "Bearer "+ token);
		
		
		Optional<User> user = repository.findByLogin(username);
		User usuario = user.get();
		usuario.setLastLogin(new Date());
		repository.save(usuario);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(json());
	}

	private CharSequence json() {
		return "{"
				+ "\"errorcode\": 401, "
				+ "\"message\": \"Invalid login or password\"}";
	}

}
