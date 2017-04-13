package com.anastasko.lnucompass.model.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserAccount extends AbstractEntity {
	
	@Column(length = 255)
	private String username;
	
	@Column(length = 255)
	private String email;

	@Column(length = 255)
	private String firstName;

	@Column(length = 255)
	private String lastName;

	@Column(length = 255)
	private String password;
	
	@Basic
	private String token;
	
	@Basic
	private Date tokenExpires;

	public Date getTokenExpires() {
		return tokenExpires;
	}

	public void setTokenExpires(Date tokenExpires) {
		this.tokenExpires = tokenExpires;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
