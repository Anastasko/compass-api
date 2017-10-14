
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.view.ItemKindViewService;
import com.anastasko.lnucompass.api.model.view.EntityItemKindViewModel;
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

@Api(tags = "Item Kind")
@RestController
@RequestMapping("/itemKind")
public class ItemKindController {

    @Autowired
    private ItemKindViewService itemKindViewService;

    @GetMapping
    public List<EntityItemKindViewModel> findAll() {
        return itemKindViewService.findAll();
    }

    @PostMapping
    public Long create(
        @RequestBody
        EntityItemKindViewModel item) {
        return itemKindViewService.create(item);
    }

    @PostMapping("/findMany")
    public List<EntityItemKindViewModel> findMany(
        @RequestBody
        List<Long> ids) {
        return itemKindViewService.findMany(ids);
    }

    @GetMapping("/{id}")
    public EntityItemKindViewModel findOne(
        @PathVariable("id")
        Long id) {
        return itemKindViewService.findOne(id);
    }

    @PutMapping
    public void update(
        @RequestBody
        EntityItemKindViewModel item) {
        itemKindViewService.update(item);
    }

    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        itemKindViewService.delete(id);
    }

}
