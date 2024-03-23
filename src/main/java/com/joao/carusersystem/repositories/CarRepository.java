package com.joao.carusersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joao.carusersystem.models.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{

}
