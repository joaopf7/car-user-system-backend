package com.joao.carusersystem.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joao.carusersystem.dtos.CarDTO;
import com.joao.carusersystem.models.Car;
import com.joao.carusersystem.services.CarService;

@RestController
@RequestMapping(value="/cars")
public class CarResource {
	
	@Autowired
	private CarService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CarDTO> findById(@PathVariable Integer id){
		Car car = service.findById(id);
		return ResponseEntity.ok().body(new CarDTO(car));
	}
	
	@GetMapping
	public ResponseEntity<List<CarDTO>> findAll(){
		List<Car> cars = service.findAll();
		List<CarDTO> carsDTO = cars.stream().map( car -> new CarDTO(car)).collect(Collectors.toList());
		return ResponseEntity.ok().body(carsDTO);
	}
	
	@PostMapping
	public ResponseEntity<CarDTO> create(@Valid @RequestBody CarDTO carDTO){
		Car newCar = service.create(carDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCar.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CarDTO> update(@PathVariable Integer id, @Valid @RequestBody CarDTO carDTO){
		Car newCar = service.update(id, carDTO);
		return ResponseEntity.ok().body(new CarDTO(newCar));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CarDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
