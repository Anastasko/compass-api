package com.anastasko.lnucompass.model.domain;

import java.util.*;

import javax.persistence.*;

import com.anastasko.lnucompass.model.view.AuthViewModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.anastasko.lnucompass.model.enums.SocialProvider;
import org.springframework.transaction.annotation.Transactional;

@Entity
public class SocialUserAccount extends AbstractEntity {
	
	@Basic
	private String userId;

	@Basic
	private String firstName;

	@Basic
	private String lastName;

	@MapKeyColumn(name = "deviceUUID")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	private Map<String, SocialUserLogin> logins = new HashMap<>();

	@Basic
	private SocialProvider provider;

    @Basic
    private String imageUrl;

    public Map<String, SocialUserLogin> getLogins() {
        return logins;
    }

    public void setLogins(Map<String, SocialUserLogin> logins) {
        this.logins = logins;
    }

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

	@Transactional
    public void setLogin(String deviceUUID, String token) {
        if (!getLogins().containsKey(deviceUUID)){
            SocialUserLogin login = new SocialUserLogin();
            login.setOwner(this);
            login.setDeviceUUID(deviceUUID);
            getLogins().put(deviceUUID, login);
        }
        getLogins().get(deviceUUID).setToken(token);
    }

}
