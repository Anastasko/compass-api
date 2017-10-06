
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.IosIconViewService;
import com.anastasko.lnucompass.api.model.view.EntityIosIconViewModel;
import com.anastasko.lnucompass.model.view.LongIdsList;
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

@Api(tags = "Ios Icon")
@RestController
@RequestMapping("/iosIcon")
public class IosIconController {

    @Autowired
    private IosIconViewService iosIconViewService;

    @GetMapping
    public List<EntityIosIconViewModel> findAll() {
        return iosIconViewService.findAll();
    }

    @PostMapping
    public Long create(
        @RequestBody
        EntityIosIconViewModel item) {
        return iosIconViewService.create(item);
    }

    @PostMapping("/findMany")
    public List<EntityIosIconViewModel> findMany(
        @RequestBody
        LongIdsList ids) {
        return iosIconViewService.findMany(ids.getIds());
    }

    @GetMapping("/{id}")
    public EntityIosIconViewModel findOne(
        @PathVariable("id")
        Long id) {
        return iosIconViewService.findOne(id);
    }

    @PutMapping
    public void update(
        @RequestBody
        EntityIosIconViewModel item) {
        iosIconViewService.update(item);
    }

    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        iosIconViewService.delete(id);
    }

}
