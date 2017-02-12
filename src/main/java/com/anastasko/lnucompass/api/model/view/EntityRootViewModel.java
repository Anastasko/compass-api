
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityRoot;
import com.anastasko.lnucompass.model.view.AbstractEntitiesViewModel;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "Root")
public class EntityRootViewModel
    extends AbstractEntityViewModel
{

    @ApiObjectField
    private AbstractEntitiesViewModel cityItems;

    public EntityRootViewModel() {
        setCityItems(new AbstractEntitiesViewModel());
    }

    public EntityRootViewModel(EntityRoot item) {
        super(item);
        setCityItems(new AbstractEntitiesViewModel(item.getCityItems()));
    }

    public AbstractEntitiesViewModel getCityItems() {
        return cityItems;
    }

    public void setCityItems(AbstractEntitiesViewModel cityItems) {
        this.cityItems = cityItems;
    }

}
