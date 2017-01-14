package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;

@ApiObject(name="AuthViewModel")
public class AuthViewModel {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
