
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.FacultyViewService;
import com.anastasko.lnucompass.api.model.view.EntityFacultyViewModel;
import com.anastasko.lnucompass.model.view.LongIdsList;
import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(name = "Faculty", description = "Faculty")
@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyViewService facultyViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EntityFacultyViewModel> findAll() {
        return facultyViewService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long create(
        @RequestBody
        EntityFacultyViewModel item) {
        return facultyViewService.create(item);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/findMany")
    public List<EntityFacultyViewModel> findMany(
        @RequestBody
        LongIdsList ids) {
        return facultyViewService.findMany(ids.getIds());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public EntityFacultyViewModel findOne(
        @PathVariable("id")
        Long id) {
        return facultyViewService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(
        @RequestBody
        EntityFacultyViewModel item) {
        facultyViewService.update(item);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        facultyViewService.delete(id);
    }

}
