package com.joao.carusersystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.carusersystem.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByLogin(String login);

	Optional<User> findByEmail(String email);
}
