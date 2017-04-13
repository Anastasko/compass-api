package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.anastasko.lnucompass.model.domain.UserAccount;
import com.anastasko.lnucompass.model.enums.SocialProvider;

@ApiObject(name="User", description="User")
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
	
	public UserViewModel(){
		
	}
	
	public UserViewModel(UserAccount user) {
		super(user);
		setUsername(user.getUsername());
		setEmail(user.getEmail());
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setToken(user.getToken());
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
