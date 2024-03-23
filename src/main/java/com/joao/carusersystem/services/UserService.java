package com.joao.carusersystem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.carusersystem.models.User;
import com.joao.carusersystem.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User findById(Integer id) {
		Optional<User> user = repository.findById(id);
		return user.orElse(null);
	}

}
