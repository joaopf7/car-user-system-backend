package com.joao.carusersystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.carusersystem.models.Car;
import com.joao.carusersystem.models.User;

public interface CarRepository extends JpaRepository<Car, Integer>{
	
	Optional<Car> findByLicensePlate(String login);

}
