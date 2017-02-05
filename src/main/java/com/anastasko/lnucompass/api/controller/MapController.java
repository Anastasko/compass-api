
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.MapViewService;
import com.anastasko.lnucompass.api.model.view.EntityMapViewModel;
import com.anastasko.lnucompass.model.view.LongIdsList;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(name = "Map", description = "Map")
@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    private MapViewService mapViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EntityMapViewModel> findAll() {
        return mapViewService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/findMany")
    public List<EntityMapViewModel> findMany(
        @RequestBody
        LongIdsList ids) {
        return mapViewService.findMany(ids.getIds());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public EntityMapViewModel findOne(
        @PathVariable("id")
        Long id) {
        return mapViewService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long create(
        @RequestBody
        EntityMapViewModel item) {
        return mapViewService.create(item);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(
        @RequestBody
        EntityMapViewModel item) {
        mapViewService.update(item);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        mapViewService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public List<ObjectNode> forSynchronisation(
        @RequestBody
        LongIdsList ids) {
        return mapViewService.findForSynchronisation(ids.getIds());
    }

}
