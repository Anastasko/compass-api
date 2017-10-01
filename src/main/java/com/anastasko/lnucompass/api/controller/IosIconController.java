
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.IosIconViewService;
import com.anastasko.lnucompass.api.model.view.EntityIosIconViewModel;
import com.anastasko.lnucompass.model.view.LongIdsList;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(name = "Ios Icon", description = "Ios Icon")
@RestController
@RequestMapping("/iosIcon")
public class IosIconController {

    @Autowired
    private IosIconViewService iosIconViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EntityIosIconViewModel> findAll() {
        return iosIconViewService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public List<ObjectNode> forSynchronisation(
        @RequestBody
        LongIdsList ids) {
        return iosIconViewService.findForSynchronisation(ids.getIds());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long create(
        @RequestBody
        EntityIosIconViewModel item) {
        return iosIconViewService.create(item);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/findMany")
    public List<EntityIosIconViewModel> findMany(
        @RequestBody
        LongIdsList ids) {
        return iosIconViewService.findMany(ids.getIds());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public EntityIosIconViewModel findOne(
        @PathVariable("id")
        Long id) {
        return iosIconViewService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(
        @RequestBody
        EntityIosIconViewModel item) {
        iosIconViewService.update(item);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        iosIconViewService.delete(id);
    }

}
