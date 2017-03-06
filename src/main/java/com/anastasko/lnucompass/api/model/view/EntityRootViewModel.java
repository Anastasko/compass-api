
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityRoot;
import com.anastasko.lnucompass.model.view.AbstractEntityViewModel;
import org.jsondoc.core.annotation.ApiObject;

@ApiObject(name = "Root")
public class EntityRootViewModel
    extends AbstractEntityViewModel
{


    public EntityRootViewModel() {
    }

    public EntityRootViewModel(EntityRoot item) {
        super(item);
    }

}
