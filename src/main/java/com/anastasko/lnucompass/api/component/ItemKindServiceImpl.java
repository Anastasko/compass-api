
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.ItemKindService;
import com.anastasko.lnucompass.api.model.domain.EntityItemKind;
import com.anastasko.lnucompass.implementation.AbstractContentEntityServiceImpl;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.springframework.stereotype.Service;

@Service
public class ItemKindServiceImpl
    extends AbstractContentEntityServiceImpl<EntityItemKind>
    implements ItemKindService
{


    @Override
    public Class<EntityItemKind> getEntityClass() {
        return EntityItemKind.class;
    }

    @Override
    public EntityTypeName getEntityTypeName() {
        return EntityTypeName.ITEM_KIND;
    }

    @Override
    public EntityItemKind newInstance() {
        return new EntityItemKind();
    }

}
