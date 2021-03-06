
package com.anastasko.lnucompass.api.component.view;

import com.anastasko.lnucompass.api.infrastructure.service.CityItemService;
import com.anastasko.lnucompass.api.infrastructure.service.FacultyService;
import com.anastasko.lnucompass.api.infrastructure.view.FacultyViewService;
import com.anastasko.lnucompass.api.model.domain.EntityFaculty;
import com.anastasko.lnucompass.api.model.view.EntityFacultyViewModel;
import com.anastasko.lnucompass.implementation.AbstractViewServiceImpl;
import com.anastasko.lnucompass.infrastructure.ContentEntityService;
import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacultyViewServiceImpl
    extends AbstractViewServiceImpl<EntityFaculty, EntityFacultyViewModel>
    implements FacultyViewService
{

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UrlResourceViewService urlResourceViewService;
    @Autowired
    private CityItemService cityItemService;
    @Autowired
    private FacultyService facultyService;

    @Override
    @Transactional
    public EntityFacultyViewModel toView(EntityFaculty entity) {
        return new EntityFacultyViewModel(entity);
    }

    @Override
    @Transactional
    public void mergeFields(EntityFaculty entity, EntityFacultyViewModel item) {
        entity.setName(item.getName());
        entity.setPhone(item.getPhone());
        entity.setEmail(item.getEmail());
        entity.setWebsite(item.getWebsite());
        urlResourceViewService.mergeFields(entity.getIcon(), item.getIcon());
        entity.setOwner(((item.getOwner() == null)?null:cityItemService.getReference(item.getOwner().getId())));
    }

    @Override
    @Transactional
    public ContentEntityService<EntityFaculty> getEntityService() {
        return facultyService;
    }

}
