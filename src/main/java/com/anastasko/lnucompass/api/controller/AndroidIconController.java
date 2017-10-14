
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.view.AndroidIconViewService;
import com.anastasko.lnucompass.api.model.view.EntityAndroidIconViewModel;
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

@Api(tags = "Android Icon")
@RestController
@RequestMapping("/androidIcon")
public class AndroidIconController {

    @Autowired
    private AndroidIconViewService androidIconViewService;

    @GetMapping
    public List<EntityAndroidIconViewModel> findAll() {
        return androidIconViewService.findAll();
    }

    @PostMapping
    public Long create(
        @RequestBody
        EntityAndroidIconViewModel item) {
        return androidIconViewService.create(item);
    }

    @PostMapping("/findMany")
    public List<EntityAndroidIconViewModel> findMany(
        @RequestBody
        List<Long> ids) {
        return androidIconViewService.findMany(ids);
    }

    @GetMapping("/{id}")
    public EntityAndroidIconViewModel findOne(
        @PathVariable("id")
        Long id) {
        return androidIconViewService.findOne(id);
    }

    @PutMapping
    public void update(
        @RequestBody
        EntityAndroidIconViewModel item) {
        androidIconViewService.update(item);
    }

    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        androidIconViewService.delete(id);
    }

}
