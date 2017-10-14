
package com.anastasko.lnucompass.api.model.view.sync;

import java.util.List;
import com.anastasko.lnucompass.api.model.view.EntityAndroidIconViewModel;
import com.anastasko.lnucompass.api.model.view.EntityCityItemViewModel;
import com.anastasko.lnucompass.api.model.view.EntityFacultyViewModel;
import com.anastasko.lnucompass.api.model.view.EntityIosIconViewModel;
import com.anastasko.lnucompass.api.model.view.EntityItemKindViewModel;
import com.anastasko.lnucompass.api.model.view.EntityMapItemViewModel;
import com.anastasko.lnucompass.api.model.view.EntityMapViewModel;

public class SyncActiveViewModel {

    private List<EntityItemKindViewModel> itemKind;
    private List<EntityIosIconViewModel> iosIcon;
    private List<EntityAndroidIconViewModel> androidIcon;
    private List<EntityCityItemViewModel> cityItem;
    private List<EntityMapViewModel> map;
    private List<EntityMapItemViewModel> mapItem;
    private List<EntityFacultyViewModel> faculty;

    public List<EntityItemKindViewModel> getItemKind() {
        return itemKind;
    }

    public void setItemKind(List<EntityItemKindViewModel> items) {
        itemKind = items;
    }

    public List<EntityIosIconViewModel> getIosIcon() {
        return iosIcon;
    }

    public void setIosIcon(List<EntityIosIconViewModel> items) {
        iosIcon = items;
    }

    public List<EntityAndroidIconViewModel> getAndroidIcon() {
        return androidIcon;
    }

    public void setAndroidIcon(List<EntityAndroidIconViewModel> items) {
        androidIcon = items;
    }

    public List<EntityCityItemViewModel> getCityItem() {
        return cityItem;
    }

    public void setCityItem(List<EntityCityItemViewModel> items) {
        cityItem = items;
    }

    public List<EntityMapViewModel> getMap() {
        return map;
    }

    public void setMap(List<EntityMapViewModel> items) {
        map = items;
    }

    public List<EntityMapItemViewModel> getMapItem() {
        return mapItem;
    }

    public void setMapItem(List<EntityMapItemViewModel> items) {
        mapItem = items;
    }

    public List<EntityFacultyViewModel> getFaculty() {
        return faculty;
    }

    public void setFaculty(List<EntityFacultyViewModel> items) {
        faculty = items;
    }

}
