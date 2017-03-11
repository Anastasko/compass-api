
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.model.view.AbstractContentEntityViewModel;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "City Item")
public class EntityCityItemViewModel
    extends AbstractContentEntityViewModel
{

    @ApiObjectField
    private String name;
    @ApiObjectField
    private Double longitude;
    @ApiObjectField
    private Double latitude;
    @ApiObjectField
    private String address;
    @ApiObjectField
    private AbstractEntityViewModel kind;
    @ApiObjectField
    private AbstractEntityViewModel owner;

    public EntityCityItemViewModel() {
    }

    public EntityCityItemViewModel(EntityCityItem item) {
        super(item);
        setName(item.getName());
        setLongitude(item.getLongitude());
        setLatitude(item.getLatitude());
        setAddress(item.getAddress());
        setKind(new AbstractEntityViewModel(item.getKind()));
        setOwner(new AbstractEntityViewModel(item.getOwner()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public AbstractEntityViewModel getOwner() {
        return owner;
    }

    public void setOwner(AbstractEntityViewModel owner) {
        this.owner = owner;
    }

}
