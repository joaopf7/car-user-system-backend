package com.joao.carusersystem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joao.carusersystem.models.User;
import com.joao.carusersystem.repositories.UserRepository;
import com.joao.carusersystem.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = repository.findByLogin(email);
		if(user.isPresent()) {
			return new UserSS(user.get().getId(), user.get().getLogin(), user.get().getPassword(), null);
		}
		throw new UsernameNotFoundException(email);
	}

}