
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;

public class EntityCityItemViewModel
    extends AbstractEntityViewModel
{

    private String name;
    private String placeId;
    private Double longitude;
    private Double latitude;
    private String address;
    private AbstractEntityViewModel kind;

    public EntityCityItemViewModel() {
    }

    public EntityCityItemViewModel(EntityCityItem item) {
        super(item);
        setName(item.getName());
        setPlaceId(item.getPlaceId());
        setLongitude(item.getLongitude());
        setLatitude(item.getLatitude());
        setAddress(item.getAddress());
        setKind(new AbstractEntityViewModel(item.getKind()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AbstractEntityViewModel getKind() {
        return kind;
    }

    public void setKind(AbstractEntityViewModel kind) {
        this.kind = kind;
    }

}
