package com.joao.carusersystem.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.joao.carusersystem.dtos.UserDTO;
import com.joao.carusersystem.exceptions.DataIntegrationViolationException;
import com.joao.carusersystem.exceptions.NotFoundException;
import com.joao.carusersystem.models.User;
import com.joao.carusersystem.repositories.UserRepository;
import com.joao.carusersystem.security.JWTUtil;

@Service
public class UserService {
	
	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public User findById(Integer id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new NotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public User create(UserDTO userDTO) {
		userDTO.setId(null);
		userDTO.setPassword(encoder.encode(userDTO.getPassword()));
		validFields(userDTO);
		User newUser = new User(userDTO);
		return repository.save(newUser);
	}
	
	public User update(Integer id, @Valid UserDTO userDTO) {
		userDTO.setId(id);
		User oldUser = findById(id);
		if(!userDTO.getPassword().equals(oldUser.getPassword())) {
			userDTO.setPassword(encoder.encode(userDTO.getPassword()));
		}
		validFields(userDTO);
		oldUser = new User(userDTO);
		return repository.save(oldUser);
	}
	
	public void delete(Integer id) {
		User user = findById(id);
		if (user.getCars().size() > 0) {
			throw new DataIntegrationViolationException("Usuário possui carros! Por isso não pode ser deletado");
		} 
		repository.deleteById(id);
	}

	private void validFields(UserDTO userDTO) {
		Optional<User> user = repository.findByLogin(userDTO.getLogin());
		if(user.isPresent() && user.get().getId() != userDTO.getId()) {
			throw new DataIntegrationViolationException("Login already exists");
		}
		
		user = repository.findByEmail(userDTO.getEmail());
		if(user.isPresent() && user.get().getId() != userDTO.getId()) {
			throw new DataIntegrationViolationException("Email already exists");
		}
	}

	public User infoMe(HttpServletRequest request) {
		String login = jwtUtil.getUsername(request.getHeader("Authorization").substring(7));
		Optional<User> user = repository.findByLogin(login);
		return user.get();
	}

	

}
