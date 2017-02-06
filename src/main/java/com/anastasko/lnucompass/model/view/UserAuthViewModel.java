package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.annotation.flow.ApiFlow;

@ApiObject(name="UserAuth", description = "User", show = false)
public class UserAuthViewModel {

	@ApiObjectField
	private String username;
	
	@ApiObjectField
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
