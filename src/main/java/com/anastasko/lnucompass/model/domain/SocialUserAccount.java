package com.anastasko.lnucompass.model.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.anastasko.lnucompass.model.enums.SocialProvider;

@Entity
public class SocialUserAccount extends AbstractEntity {
	
	@Basic
	private String userId;

	@Basic
	private String firstName;

	@Basic
	private String lastName;

	@Basic
	private String token;

	@Basic
	private SocialProvider provider;

	@Basic
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

}
