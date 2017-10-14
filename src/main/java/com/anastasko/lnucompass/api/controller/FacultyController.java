
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.view.FacultyViewService;
import com.anastasko.lnucompass.api.model.view.EntityFacultyViewModel;
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

@Api(tags = "Faculty")
@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyViewService facultyViewService;

    @GetMapping
    public List<EntityFacultyViewModel> findAll() {
        return facultyViewService.findAll();
    }

    @PostMapping
    public Long create(
        @RequestBody
        EntityFacultyViewModel item) {
        return facultyViewService.create(item);
    }

    @PostMapping("/findMany")
    public List<EntityFacultyViewModel> findMany(
        @RequestBody
        List<Long> ids) {
        return facultyViewService.findMany(ids);
    }

    @GetMapping("/{id}")
    public EntityFacultyViewModel findOne(
        @PathVariable("id")
        Long id) {
        return facultyViewService.findOne(id);
    }

    @PutMapping
    public void update(
        @RequestBody
        EntityFacultyViewModel item) {
        facultyViewService.update(item);
    }

    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        facultyViewService.delete(id);
    }

}
