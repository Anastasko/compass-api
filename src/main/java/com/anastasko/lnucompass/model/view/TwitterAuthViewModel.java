package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "TwitterAuth", description = "Twitter specific auth view model", show = false)
public class TwitterAuthViewModel extends AuthViewModel {

	private String tokenSecret;

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

}
