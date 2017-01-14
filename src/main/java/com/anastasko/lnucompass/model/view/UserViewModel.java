package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.anastasko.lnucompass.model.enums.SocialMediaService;

@ApiObject(name="User")
public class UserViewModel extends AbstractEntityViewModel {

	@ApiObjectField
	private String username;
	
	@ApiObjectField
	private String email;

	@ApiObjectField
	private String firstName;

	@ApiObjectField
	private String lastName;

	@ApiObjectField
	private String password;
	
	@ApiObjectField
	private String token;

	@ApiObjectField
	private SocialMediaService signInProvider;
	
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

	public SocialMediaService getSignInProvider() {
		return signInProvider;
	}

	public void setSignInProvider(SocialMediaService signInProvider) {
		this.signInProvider = signInProvider;
	}

}
