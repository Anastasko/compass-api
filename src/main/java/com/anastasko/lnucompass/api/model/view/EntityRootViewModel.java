
package com.anastasko.lnucompass.api.model.view;

import com.anastasko.lnucompass.api.model.domain.EntityRoot;
import com.anastasko.lnucompass.model.view.AbstractContentEntityViewModel;
import org.jsondoc.core.annotation.ApiObject;

@ApiObject(name = "Root")
public class EntityRootViewModel
    extends AbstractContentEntityViewModel
{


    public EntityRootViewModel() {
    }

    public EntityRootViewModel(EntityRoot item) {
        super(item);
    }

}
