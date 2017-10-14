
package com.anastasko.lnucompass.api.component.sync;

import com.anastasko.lnucompass.api.infrastructure.service.AndroidIconService;
import com.anastasko.lnucompass.api.infrastructure.service.CityItemService;
import com.anastasko.lnucompass.api.infrastructure.service.FacultyService;
import com.anastasko.lnucompass.api.infrastructure.service.IosIconService;
import com.anastasko.lnucompass.api.infrastructure.service.ItemKindService;
import com.anastasko.lnucompass.api.infrastructure.service.MapItemService;
import com.anastasko.lnucompass.api.infrastructure.service.MapService;
import com.anastasko.lnucompass.api.infrastructure.view.AndroidIconViewService;
import com.anastasko.lnucompass.api.infrastructure.view.CityItemViewService;
import com.anastasko.lnucompass.api.infrastructure.view.FacultyViewService;
import com.anastasko.lnucompass.api.infrastructure.view.IosIconViewService;
import com.anastasko.lnucompass.api.infrastructure.view.ItemKindViewService;
import com.anastasko.lnucompass.api.infrastructure.view.MapItemViewService;
import com.anastasko.lnucompass.api.infrastructure.view.MapViewService;
import com.anastasko.lnucompass.api.model.view.sync.SyncCountViewModel;
import com.anastasko.lnucompass.implementation.AbstractSyncService;
import com.anastasko.lnucompass.infrastructure.SyncService;
import com.anastasko.lnucompass.model.view.SyncModels;
import com.anastasko.lnucompass.model.view.SyncViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SyncServiceImpl
    extends AbstractSyncService
    implements SyncService
{

    @Autowired
    private ItemKindViewService itemKindViewService;
    @Autowired
    private IosIconViewService iosIconViewService;
    @Autowired
    private AndroidIconViewService androidIconViewService;
    @Autowired
    private CityItemViewService cityItemViewService;
    @Autowired
    private MapViewService mapViewService;
    @Autowired
    private MapItemViewService mapItemViewService;
    @Autowired
    private FacultyViewService facultyViewService;
    @Autowired
    private ItemKindService itemKindService;
    @Autowired
    private IosIconService iosIconService;
    @Autowired
    private AndroidIconService androidIconService;
    @Autowired
    private CityItemService cityItemService;
    @Autowired
    private MapService mapService;
    @Autowired
    private MapItemService mapItemService;
    @Autowired
    private FacultyService facultyService;

    @Override
    public SyncCountViewModel count() {
        SyncCountViewModel result = new SyncCountViewModel();
        result.setItemKind((0L + itemKindViewService.findAll().size()));
        result.setIosIcon((0L + iosIconViewService.findAll().size()));
        result.setAndroidIcon((0L + androidIconViewService.findAll().size()));
        result.setCityItem((0L + cityItemViewService.findAll().size()));
        result.setMap((0L + mapViewService.findAll().size()));
        result.setMapItem((0L + mapItemViewService.findAll().size()));
        result.setFaculty((0L + facultyViewService.findAll().size()));
        return result;
    }

    @Override
    public SyncViewModel sync(SyncModels models) {
        SyncViewModel result = new SyncViewModel();
        sync(models, itemKindService, itemKindViewService, result.getActive()::setItemKind, result.getDeleted()::setItemKind);
        sync(models, iosIconService, iosIconViewService, result.getActive()::setIosIcon, result.getDeleted()::setIosIcon);
        sync(models, androidIconService, androidIconViewService, result.getActive()::setAndroidIcon, result.getDeleted()::setAndroidIcon);
        sync(models, cityItemService, cityItemViewService, result.getActive()::setCityItem, result.getDeleted()::setCityItem);
        sync(models, mapService, mapViewService, result.getActive()::setMap, result.getDeleted()::setMap);
        sync(models, mapItemService, mapItemViewService, result.getActive()::setMapItem, result.getDeleted()::setMapItem);
        sync(models, facultyService, facultyViewService, result.getActive()::setFaculty, result.getDeleted()::setFaculty);
        return result;
    }

}
