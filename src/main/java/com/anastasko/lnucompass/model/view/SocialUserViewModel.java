package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.model.domain.SocialUserAccount;
import com.anastasko.lnucompass.model.enums.SocialProvider;

public class SocialUserViewModel extends AbstractEntityViewModel {

	private String firstName;

	private String lastName;

    private String token;

    private SocialProvider provider;

    private String imageUrl;

    public SocialUserViewModel(SocialProvider provider){
        setProvider(provider);
    }

    public SocialUserViewModel(SocialUserAccount user, String deviceUUID){
        super(user);
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
    	setToken(user.getLogins().get(deviceUUID).getToken());
    	setProvider(user.getProvider());
    	setImageUrl(user.getImageUrl());
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
