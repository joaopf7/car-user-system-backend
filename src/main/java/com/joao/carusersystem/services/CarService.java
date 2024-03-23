package com.joao.carusersystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.carusersystem.dtos.CarDTO;
import com.joao.carusersystem.dtos.UserDTO;
import com.joao.carusersystem.exceptions.DataIntegrationViolationException;
import com.joao.carusersystem.exceptions.NotFoundException;
import com.joao.carusersystem.models.Car;
import com.joao.carusersystem.models.User;
import com.joao.carusersystem.repositories.CarRepository;

import jakarta.validation.Valid;

@Service
public class CarService {
	
	@Autowired
	private CarRepository repository;
	
	@Autowired
	private UserService userService;
	
	public Car findById(Integer id) {
		Optional<Car> car = repository.findById(id);
		return car.orElseThrow(() -> new NotFoundException("Objeto não encontrado! ID: "+ id));
	}

	public List<Car> findAll() {
		return repository.findAll();
	}

	public Car create(@Valid CarDTO carDTO) {
		carDTO.setId(null);
		return repository.save(newCar(carDTO));
	}
	
	public Car update(Integer id, @Valid CarDTO carDTO) {
		carDTO.setId(id);
		Car oldCar = findById(id);
		oldCar = newCar(carDTO);
		return repository.save(oldCar);
	}
	
	public Car newCar(CarDTO carDTO) {
		User user = userService.findById(carDTO.getIdUser());
		validFields(carDTO);
		Car car = new Car();
		if (carDTO.getId() != null) {
			car.setId(carDTO.getId());
		}
		car.setUser(user);
		car.setColor(carDTO.getColor());
		car.setLicensePlate(carDTO.getLicensePlate());
		car.setModel(carDTO.getModel());
		car.setYear(carDTO.getYear());
		return car;
	}
	
	private void validFields(CarDTO carDTO) {
		Optional<Car> car = repository.findByLicensePlate(carDTO.getLicensePlate());
		if(car.isPresent() && car.get().getId() != carDTO.getId()) {
			throw new DataIntegrationViolationException("Placa já cadastrada no sistema");
		}
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	

}
