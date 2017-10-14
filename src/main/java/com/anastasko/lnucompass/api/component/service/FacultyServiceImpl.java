
package com.anastasko.lnucompass.api.component.service;

import com.anastasko.lnucompass.api.infrastructure.service.FacultyService;
import com.anastasko.lnucompass.api.model.domain.EntityFaculty;
import com.anastasko.lnucompass.implementation.AbstractContentEntityServiceImpl;
import com.anastasko.lnucompass.model.enums.EntityTypeName;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl
    extends AbstractContentEntityServiceImpl<EntityFaculty>
    implements FacultyService
{


    @Override
    public Class<EntityFaculty> getEntityClass() {
        return EntityFaculty.class;
    }

    @Override
    public EntityTypeName getEntityTypeName() {
        return EntityTypeName.FACULTY;
    }

    @Override
    public EntityFaculty newInstance() {
        return new EntityFaculty();
    }

}
