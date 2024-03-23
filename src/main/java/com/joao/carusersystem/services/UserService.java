package com.joao.carusersystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.carusersystem.dtos.UserDTO;
import com.joao.carusersystem.exceptions.DataIntegrationViolationException;
import com.joao.carusersystem.exceptions.NotFoundException;
import com.joao.carusersystem.models.User;
import com.joao.carusersystem.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User findById(Integer id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new NotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User create(UserDTO userDTO) {
		userDTO.setId(null);
		validFields(userDTO);
		User newUser = new User(userDTO);
		return repository.save(newUser);
	}

	private void validFields(UserDTO userDTO) {
		Optional<User> user = repository.findByLogin(userDTO.getLogin());
		if(user.isPresent() && user.get().getId() != userDTO.getId()) {
			throw new DataIntegrationViolationException("Login já cadastrado no sistema");
		}
		
		user = repository.findByEmail(userDTO.getEmail());
		if(user.isPresent() && user.get().getId() != userDTO.getId()) {
			throw new DataIntegrationViolationException("E-mail já cadastrado no sistema");
		}
	}

}
