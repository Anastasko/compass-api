package com.anastasko.lnucompass.model.view;

import java.util.Date;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.anastasko.lnucompass.model.domain.SocialUserAccount;
import com.anastasko.lnucompass.model.enums.SocialProvider;

@ApiObject(name="SocialUser")
public class SocialUserViewModel extends AbstractEntityViewModel {

	@ApiObjectField
	private String firstName;

	@ApiObjectField
    private String lastName;

    @ApiObjectField
    private String token;

    @ApiObjectField
    private SocialProvider provider;

    @ApiObjectField
    private String imageUrl;

    public SocialUserViewModel(SocialProvider provider){
        setProvider(provider);
    }

    public SocialUserViewModel(SocialUserAccount user){
        super(user);
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
    	setToken(user.getToken());
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
