
package com.anastasko.lnucompass.api.model.view.sync;

import java.util.List;

public class SyncDeletedViewModel {

    private List<Long> itemKind;
    private List<Long> iosIcon;
    private List<Long> androidIcon;
    private List<Long> cityItem;
    private List<Long> map;
    private List<Long> mapItem;
    private List<Long> faculty;

    public List<Long> getItemKind() {
        return itemKind;
    }

    public void setItemKind(List<Long> items) {
        itemKind = items;
    }

    public List<Long> getIosIcon() {
        return iosIcon;
    }

    public void setIosIcon(List<Long> items) {
        iosIcon = items;
    }

    public List<Long> getAndroidIcon() {
        return androidIcon;
    }

    public void setAndroidIcon(List<Long> items) {
        androidIcon = items;
    }

    public List<Long> getCityItem() {
        return cityItem;
    }

    public void setCityItem(List<Long> items) {
        cityItem = items;
    }

    public List<Long> getMap() {
        return map;
    }

    public void setMap(List<Long> items) {
        map = items;
    }

    public List<Long> getMapItem() {
        return mapItem;
    }

    public void setMapItem(List<Long> items) {
        mapItem = items;
    }

    public List<Long> getFaculty() {
        return faculty;
    }

    public void setFaculty(List<Long> items) {
        faculty = items;
    }

}
