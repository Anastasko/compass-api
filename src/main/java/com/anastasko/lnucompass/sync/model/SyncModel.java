package com.anastasko.lnucompass.sync.model;

import java.util.*;

public class SyncModel {

    private Range range = new Range();

    private Set<Long> cityItems = new HashSet<>();

    public SyncModel(long from, long to) {
        getRange().setFrom(from);
        getRange().setTo(to);
    }

    public SyncModel() {

    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public Set<Long> getCityItems() {
        return cityItems;
    }

    public void setCityItems(Set<Long> cityItems) {
        this.cityItems = cityItems;
    }

    public boolean empty() {
        return getCityItems().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SyncModel)) return false;

        SyncModel syncModel = (SyncModel) o;

        if (!getRange().equals(syncModel.getRange())) return false;
        return getCityItems().equals(syncModel.getCityItems());
    }

    @Override
    public String toString() {
        return "SyncModel{" +
                "range=" + range +
                ", cityItems=" + cityItems +
                '}';
    }
}
