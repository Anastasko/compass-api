
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.CityItemService;
import com.anastasko.lnucompass.api.infrastructure.CityItemViewService;
import com.anastasko.lnucompass.api.infrastructure.MapViewService;
import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.api.model.view.EntityCityItemViewModel;
import com.anastasko.lnucompass.api.model.view.EntityMapViewModel;
import com.anastasko.lnucompass.model.view.LongIdsList;
import com.anastasko.lnucompass.validation.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(name = "CityItem", description = "CityItem")
@RestController
@RequestMapping("/cityItem")
public class CityItemController {

    @Autowired
    private CityItemViewService cityItemViewService;
    @Autowired
    private CityItemService cityItemService;
    @Autowired
    private MapViewService mapViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EntityCityItemViewModel> findAll() {
        return cityItemViewService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/findMany")
    public List<EntityCityItemViewModel> findMany(
        @RequestBody
        LongIdsList ids) {
        return cityItemViewService.findMany(ids.getIds());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public EntityCityItemViewModel findOne(
        @PathVariable("id")
        Long id) {
        return cityItemViewService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long create(
        @RequestBody
        EntityCityItemViewModel item) {
        return cityItemViewService.create(item);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(
        @RequestBody
        EntityCityItemViewModel item) {
        cityItemViewService.update(item);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        cityItemViewService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/maps")
    public List<EntityMapViewModel> find_maps(
        @PathVariable("id")
        Long id) {
        EntityCityItem item = cityItemService.findOne(id, "mapsGraph");
        if (item == null) {
            throw new ResourceNotFoundException(("CityItem does not exist. id="+ id));
        }
        return mapViewService.viewModels(item.getMaps());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public List<ObjectNode> forSynchronisation(
        @RequestBody
        LongIdsList ids) {
        return cityItemViewService.findForSynchronisation(ids.getIds());
    }

}
