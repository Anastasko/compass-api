
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
    private String name;
    @ApiObjectField
    private Long floor;

    public EntityMapViewModel() {
    }

    public EntityMapViewModel(EntityMap item) {
        super(item);
        setName(item.getName());
        setFloor(item.getFloor());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFloor() {
        return floor;
    }

    public void setFloor(Long floor) {
        this.floor = floor;
    }

}
