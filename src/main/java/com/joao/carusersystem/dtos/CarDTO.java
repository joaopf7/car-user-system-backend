package com.joao.carusersystem.dtos;

import java.io.Serializable;

import com.joao.carusersystem.models.Car;

import jakarta.validation.constraints.NotNull;

public class CarDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotNull(message = "field year is required")
	private Integer year; 
	@NotNull(message = "field licensePlate is required")
	private String licensePlate; 
	@NotNull(message = "field model is required")
	private String model;
	@NotNull(message = "field color is required")
	private String color;
	@NotNull(message = "field user is required")
	private Integer idUser;
	private String nameUser;
	
	public CarDTO() {
		super();
	}
	
	public CarDTO(Car car) {
		this.id = car.getId();
		this.year = car.getYear();
		this.licensePlate = car.getLicensePlate();
		this.model = car.getLicensePlate();
		this.color = car.getColor();
		this.idUser = car.getUser().getId();
		this.nameUser = car.getUser().getFirstName() + car.getUser().getLastName();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	
	

}
