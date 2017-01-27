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
	private String name;
	
	@Basic
	private String token;

	@Basic
	private SocialProvider signInProvider;

	@Basic
	private Date expiration;
	
	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public SocialProvider getSignInProvider() {
		return signInProvider;
	}

	public void setSignInProvider(SocialProvider signInProvider) {
		this.signInProvider = signInProvider;
	}
	
}
