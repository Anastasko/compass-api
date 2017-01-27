package com.anastasko.lnucompass.model.view;

import java.util.Date;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.anastasko.lnucompass.model.domain.SocialUserAccount;
import com.anastasko.lnucompass.model.enums.SocialProvider;

@ApiObject(name="SocialUser")
public class SocialUserViewModel {

	@ApiObjectField
	private String name;

	@ApiObjectField
    private String token;

    @ApiObjectField
    private SocialProvider provider;
    
    @ApiObjectField
    private Date expiration;
    
    public SocialUserViewModel(SocialUserAccount user){
    	setName(user.getName());
    	setToken(user.getToken());
    	setProvider(user.getSignInProvider());
    	setExpiration(user.getExpiration());
    }
    
    public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SocialProvider getProvider() {
        return provider;
    }

    public void setProvider(SocialProvider provider) {
        this.provider = provider;
    }

}
