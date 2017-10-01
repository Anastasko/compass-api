
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.AndroidIconViewService;
import com.anastasko.lnucompass.api.model.view.EntityAndroidIconViewModel;
import com.anastasko.lnucompass.model.view.LongIdsList;
import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(name = "Android Icon", description = "Android Icon")
@RestController
@RequestMapping("/androidIcon")
public class AndroidIconController {

    @Autowired
    private AndroidIconViewService androidIconViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EntityAndroidIconViewModel> findAll() {
        return androidIconViewService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long create(
        @RequestBody
        EntityAndroidIconViewModel item) {
        return androidIconViewService.create(item);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/findMany")
    public List<EntityAndroidIconViewModel> findMany(
        @RequestBody
        LongIdsList ids) {
        return androidIconViewService.findMany(ids.getIds());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public EntityAndroidIconViewModel findOne(
        @PathVariable("id")
        Long id) {
        return androidIconViewService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(
        @RequestBody
        EntityAndroidIconViewModel item) {
        androidIconViewService.update(item);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        androidIconViewService.delete(id);
    }

}
