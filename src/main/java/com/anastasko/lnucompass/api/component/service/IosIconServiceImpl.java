
package com.anastasko.lnucompass.api.component.service;

import com.anastasko.lnucompass.api.infrastructure.service.IosIconService;
import com.anastasko.lnucompass.api.model.domain.EntityIosIcon;
import com.anastasko.lnucompass.implementation.AbstractContentEntityServiceImpl;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.springframework.stereotype.Service;

@Service
public class IosIconServiceImpl
    extends AbstractContentEntityServiceImpl<EntityIosIcon>
    implements IosIconService
{


    @Override
    public Class<EntityIosIcon> getEntityClass() {
        return EntityIosIcon.class;
    }

    @Override
    public EntityTypeName getEntityTypeName() {
        return EntityTypeName.IOS_ICON;
    }

    @Override
    public EntityIosIcon newInstance() {
        return new EntityIosIcon();
    }

}
