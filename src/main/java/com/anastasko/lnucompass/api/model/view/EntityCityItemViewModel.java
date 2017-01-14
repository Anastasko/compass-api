
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.model.view.AbstractEntitiesViewModel;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "CityItem")
public class EntityCityItemViewModel
    extends AbstractEntityViewModel
{

    @ApiObjectField
    private String name;
    @ApiObjectField
    private Double longitude;
    @ApiObjectField
    private Double latitude;
    @ApiObjectField
    private AbstractEntitiesViewModel maps;

    public EntityCityItemViewModel() {
        setMaps(new AbstractEntitiesViewModel());
    }

    public EntityCityItemViewModel(EntityCityItem item) {
        super(item);
        setName(item.getName());
        setLongitude(item.getLongitude());
        setLatitude(item.getLatitude());
        setMaps(new AbstractEntitiesViewModel(item.getMaps()));
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

    public AbstractEntitiesViewModel getMaps() {
        return maps;
    }

    public void setMaps(AbstractEntitiesViewModel maps) {
        this.maps = maps;
    }

}
