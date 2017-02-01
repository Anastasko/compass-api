
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityMap;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "Map")
public class EntityMapViewModel
    extends AbstractEntityViewModel
{

    @ApiObjectField
    private String imageUrl;
    @ApiObjectField
    private Long floor;
    @ApiObjectField
    private AbstractEntityViewModel owner;

    public EntityMapViewModel() {
    }

    public EntityMapViewModel(EntityMap item) {
        super(item);
        setImageUrl(item.getImageUrl());
        setFloor(item.getFloor());
        setOwner(new AbstractEntityViewModel(item.getOwner()));
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

    public AbstractEntityViewModel getOwner() {
        return owner;
    }

    public void setOwner(AbstractEntityViewModel owner) {
        this.owner = owner;
    }

}
