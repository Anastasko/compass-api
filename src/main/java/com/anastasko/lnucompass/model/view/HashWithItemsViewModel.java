package com.anastasko.lnucompass.model.view;

import java.util.Set;

public class HashWithItemsViewModel extends HashViewModel {

    private Set<Long> cityItems;

    public Set<Long> getCityItems() {
        return cityItems;
    }

    public void setCityItems(Set<Long> cityItems) {
        this.cityItems = cityItems;
    }

}
