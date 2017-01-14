package com.anastasko.lnucompass.model.view;

import com.anastasko.lnucompass.model.enums.SocialProvider;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name="User")
public class SocialUserViewModel {

    @ApiObjectField
    private String token;

    @ApiObjectField
    private SocialProvider provider;

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
