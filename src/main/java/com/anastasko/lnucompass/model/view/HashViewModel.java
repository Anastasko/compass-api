package com.anastasko.lnucompass.model.view;

import java.util.List;

public class HashViewModel {

    private String hash;

    public HashViewModel() {
    }

    public HashViewModel(String hash) {
        setHash(hash);
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
}
