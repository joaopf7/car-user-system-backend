package com.joao.carusersystem;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joao.carusersystem.models.Car;
import com.joao.carusersystem.models.User;
import com.joao.carusersystem.repositories.CarRepository;
import com.joao.carusersystem.repositories.UserRepository;

@SpringBootApplication
public class CarUserSystemApplication implements CommandLineRunner {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	CarRepository carRepository;

	public static void main(String[] args) {
		SpringApplication.run(CarUserSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User(null, "João", "Paulo", "joao@email.com", new Date(), "joao",
				"123", "12345678");
		User user2 = new User(null, "Jose", "Pedro", "jose@email.com", new Date(), "jose",
				"123", "12345678");
		
		Car car = new Car( null, 2020, "ABC1234", "HB20", "Prata",  user);
		repository.saveAll(Arrays.asList(user, user2));
		carRepository.save(car);
	}

}
