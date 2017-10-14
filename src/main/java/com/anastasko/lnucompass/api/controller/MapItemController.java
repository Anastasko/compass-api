
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.view.MapItemViewService;
import com.anastasko.lnucompass.api.model.view.EntityMapItemViewModel;
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

@Api(tags = "Map Item")
@RestController
@RequestMapping("/mapItem")
public class MapItemController {

    @Autowired
    private MapItemViewService mapItemViewService;

    @GetMapping
    public List<EntityMapItemViewModel> findAll() {
        return mapItemViewService.findAll();
    }

    @PostMapping
    public Long create(
        @RequestBody
        EntityMapItemViewModel item) {
        return mapItemViewService.create(item);
    }

    @PostMapping("/findMany")
    public List<EntityMapItemViewModel> findMany(
        @RequestBody
        List<Long> ids) {
        return mapItemViewService.findMany(ids);
    }

    @GetMapping("/{id}")
    public EntityMapItemViewModel findOne(
        @PathVariable("id")
        Long id) {
        return mapItemViewService.findOne(id);
    }

    @PutMapping
    public void update(
        @RequestBody
        EntityMapItemViewModel item) {
        mapItemViewService.update(item);
    }

    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        mapItemViewService.delete(id);
    }

}
