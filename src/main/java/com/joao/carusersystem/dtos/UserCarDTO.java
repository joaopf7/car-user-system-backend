package com.joao.carusersystem.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.joao.carusersystem.models.Car;
import com.joao.carusersystem.models.User;

public class UserCarDTO implements Serializable {
private static final long serialVersionUID = 1L;

	
	private Integer id;

	@NotNull(message = "field firstname is required")
	private String firstName;
	@NotNull(message = "field lastname is required")
	private String lastName;
	@NotNull(message = "field email is required")
	private String email;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthday;

	@NotNull(message = "field login is required")
	private String login;
	@NotNull(message = "field password is required")
	private String password;
	@NotNull(message = "field phone is required")
	private String phone;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date lastLogin;
	
	private List<Car> cars;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate createdAt = LocalDate.now();
	
	public UserCarDTO() {
		super();
	}
	
	public UserCarDTO(User user) {
		super();
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.birthday = user.getBirthday();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.phone = user.getPhone();
		this.cars = user.getCars();
		this.lastLogin = user.getLastLogin();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
}
