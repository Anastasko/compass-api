
package com.anastasko.lnucompass.api.component;

import com.anastasko.lnucompass.api.infrastructure.AndroidIconService;
import com.anastasko.lnucompass.api.model.domain.EntityAndroidIcon;
import com.anastasko.lnucompass.implementation.AbstractContentEntityServiceImpl;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.springframework.stereotype.Service;

@Service
public class AndroidIconServiceImpl
    extends AbstractContentEntityServiceImpl<EntityAndroidIcon>
    implements AndroidIconService
{


    @Override
    public Class<EntityAndroidIcon> getEntityClass() {
        return EntityAndroidIcon.class;
    }

    @Override
    public EntityTypeName getEntityTypeName() {
        return EntityTypeName.ANDROID_ICON;
    }

    @Override
    public EntityAndroidIcon newInstance() {
        return new EntityAndroidIcon();
    }

}
