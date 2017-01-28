package com.anastasko.lnucompass.model.domain;

import javax.persistence.*;

@Entity
public class SocialUserLogin extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private SocialUserAccount owner;

    @Basic
    private String token;

    @Basic
    private String deviceUUID;

    public SocialUserAccount getOwner() {
        return owner;
    }

    public void setOwner(SocialUserAccount owner) {
        this.owner = owner;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceUUID() {
        return deviceUUID;
    }

    public void setDeviceUUID(String deviceUUID) {
        this.deviceUUID = deviceUUID;
    }

}
