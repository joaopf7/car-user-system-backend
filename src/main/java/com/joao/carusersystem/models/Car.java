package com.joao.carusersystem.models;

import java.util.Objects;

public class Car {
	
	private Integer id;
	private Integer year; 
	private String licensePlate; 
	private String model;
	private String color;
	
	public Car() {
		super();
	}
	
	public Car(Integer id, Integer year, String licensePlate, String model, String color) {
		super();
		this.id = id;
		this.year = year;
		this.licensePlate = licensePlate;
		this.model = model;
		this.color = color;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(id, licensePlate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(id, other.id) && Objects.equals(licensePlate, other.licensePlate);
	}
	
	
	
	
}
