
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.ItemKindViewService;
import com.anastasko.lnucompass.api.model.view.EntityItemKindViewModel;
import com.anastasko.lnucompass.model.view.LongIdsList;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(name = "Item Kind", description = "Item Kind")
@RestController
@RequestMapping("/itemKind")
public class ItemKindController {

    @Autowired
    private ItemKindViewService itemKindViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EntityItemKindViewModel> findAll() {
        return itemKindViewService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public List<ObjectNode> forSynchronisation(
        @RequestBody
        LongIdsList ids) {
        return itemKindViewService.findForSynchronisation(ids.getIds());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long create(
        @RequestBody
        EntityItemKindViewModel item) {
        return itemKindViewService.create(item);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/findMany")
    public List<EntityItemKindViewModel> findMany(
        @RequestBody
        LongIdsList ids) {
        return itemKindViewService.findMany(ids.getIds());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public EntityItemKindViewModel findOne(
        @PathVariable("id")
        Long id) {
        return itemKindViewService.findOne(id);
    }

}