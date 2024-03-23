package com.joao.carusersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.carusersystem.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
