
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.MapItemViewService;
import com.anastasko.lnucompass.api.infrastructure.MapService;
import com.anastasko.lnucompass.api.infrastructure.MapViewService;
import com.anastasko.lnucompass.api.model.domain.EntityMap;
import com.anastasko.lnucompass.api.model.view.EntityMapItemViewModel;
import com.anastasko.lnucompass.api.model.view.EntityMapViewModel;
import com.anastasko.lnucompass.model.view.LongIdsList;
import com.anastasko.lnucompass.validation.exceptions.ResourceNotFoundException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Map")
@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    private MapViewService mapViewService;
    @Autowired
    private MapService mapService;
    @Autowired
    private MapItemViewService mapItemViewService;

    @GetMapping
    public List<EntityMapViewModel> findAll() {
        return mapViewService.findAll();
    }

    @PostMapping
    public Long create(
        @RequestBody
        EntityMapViewModel item) {
        return mapViewService.create(item);
    }

    @PostMapping("/findMany")
    public List<EntityMapViewModel> findMany(
        @RequestBody
        LongIdsList ids) {
        return mapViewService.findMany(ids.getIds());
    }

    @GetMapping("/{id}")
    public EntityMapViewModel findOne(
        @PathVariable("id")
        Long id) {
        return mapViewService.findOne(id);
    }

    @GetMapping("/{id}/mapItems")
    public List<EntityMapItemViewModel> find_mapItems(
        @PathVariable("id")
        Long id) {
        EntityMap item = mapService.findOne(id, "mapItemsGraph");
        if (item == null) {
            throw new ResourceNotFoundException(("Map does not exist. id="+ id));
        }
        return mapItemViewService.viewModels(item.getMapItems());
    }

    @PutMapping
    public void update(
        @RequestBody
        EntityMapViewModel item) {
        mapViewService.update(item);
    }

    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        mapViewService.delete(id);
    }

}
