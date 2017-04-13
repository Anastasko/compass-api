
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.MapItemViewService;
import com.anastasko.lnucompass.api.model.view.EntityMapItemViewModel;
import com.anastasko.lnucompass.model.view.LongIdsList;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(name = "Map Item", description = "Map Item")
@RestController
@RequestMapping("/mapItem")
public class MapItemController {

    @Autowired
    private MapItemViewService mapItemViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EntityMapItemViewModel> findAll() {
        return mapItemViewService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public List<ObjectNode> forSynchronisation(
        @RequestBody
        LongIdsList ids) {
        return mapItemViewService.findForSynchronisation(ids.getIds());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long create(
        @RequestBody
        EntityMapItemViewModel item) {
        return mapItemViewService.create(item);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/findMany")
    public List<EntityMapItemViewModel> findMany(
        @RequestBody
        LongIdsList ids) {
        return mapItemViewService.findMany(ids.getIds());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public EntityMapItemViewModel findOne(
        @PathVariable("id")
        Long id) {
        return mapItemViewService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(
        @RequestBody
        EntityMapItemViewModel item) {
        mapItemViewService.update(item);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        mapItemViewService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByRoom/{room}")
    public EntityMapItemViewModel findOneByRoom(
        @PathVariable("room")
        String id) {
        return mapItemViewService.findOneByAttribute("room", id);
    }

}
