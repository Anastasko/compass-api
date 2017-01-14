
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityMap;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import org.jsondoc.core.annotation.ApiObject;

@ApiObject(name = "Map")
public class EntityMapViewModel
    extends AbstractEntityViewModel
{


    public EntityMapViewModel() {
    }

    public EntityMapViewModel(EntityMap item) {
        super(item);
    }

}
