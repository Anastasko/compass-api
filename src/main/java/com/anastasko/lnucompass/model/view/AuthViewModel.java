package com.anastasko.lnucompass.model.view;

import org.jsondoc.core.annotation.ApiObject;

@ApiObject(name="Auth", description="g+ & fb auth view model")
public class AuthViewModel {

    private String token;

    private String deviceUUID;

    public String getDeviceUUID() {
        return deviceUUID;
    }

    public void setDeviceUUID(String deviceUUID) {
        this.deviceUUID = deviceUUID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
