package com.joao.carusersystem.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joao.carusersystem.dtos.UserCarDTO;
import com.joao.carusersystem.models.User;
import com.joao.carusersystem.services.UserService;

@RestController
@RequestMapping(value = "/me")
public class MeResource {
	

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<UserCarDTO> me(HttpServletRequest request) {
		User user = service.infoMe(request);
		return ResponseEntity.ok().body(new UserCarDTO(user));
	}

}
