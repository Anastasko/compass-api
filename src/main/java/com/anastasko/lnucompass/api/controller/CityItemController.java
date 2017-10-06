
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.CityItemService;
import com.anastasko.lnucompass.api.infrastructure.CityItemViewService;
import com.anastasko.lnucompass.api.infrastructure.FacultyViewService;
import com.anastasko.lnucompass.api.infrastructure.MapViewService;
import com.anastasko.lnucompass.api.model.domain.EntityCityItem;
import com.anastasko.lnucompass.api.model.view.EntityCityItemViewModel;
import com.anastasko.lnucompass.api.model.view.EntityFacultyViewModel;
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

@Api(tags = "City Item")
@RestController
@RequestMapping("/cityItem")
public class CityItemController {

    @Autowired
    private CityItemViewService cityItemViewService;
    @Autowired
    private CityItemService cityItemService;
    @Autowired
    private MapViewService mapViewService;
    @Autowired
    private FacultyViewService facultyViewService;

    @GetMapping
    public List<EntityCityItemViewModel> findAll() {
        return cityItemViewService.findAll();
    }

    @PostMapping
    public Long create(
        @RequestBody
        EntityCityItemViewModel item) {
        return cityItemViewService.create(item);
    }

    @PostMapping("/findMany")
    public List<EntityCityItemViewModel> findMany(
        @RequestBody
        LongIdsList ids) {
        return cityItemViewService.findMany(ids.getIds());
    }

    @GetMapping("/{id}")
    public EntityCityItemViewModel findOne(
        @PathVariable("id")
        Long id) {
        return cityItemViewService.findOne(id);
    }

    @GetMapping("/{id}/maps")
    public List<EntityMapViewModel> find_maps(
        @PathVariable("id")
        Long id) {
        EntityCityItem item = cityItemService.findOne(id, "mapsGraph");
        if (item == null) {
            throw new ResourceNotFoundException(("CityItem does not exist. id="+ id));
        }
        return mapViewService.viewModels(item.getMaps());
    }

    @GetMapping("/{id}/faculties")
    public List<EntityFacultyViewModel> find_faculties(
        @PathVariable("id")
        Long id) {
        EntityCityItem item = cityItemService.findOne(id, "facultiesGraph");
        if (item == null) {
            throw new ResourceNotFoundException(("CityItem does not exist. id="+ id));
        }
        return facultyViewService.viewModels(item.getFaculties());
    }

    @PutMapping
    public void update(
        @RequestBody
        EntityCityItemViewModel item) {
        cityItemViewService.update(item);
    }

    @DeleteMapping("/{id}")
    public void delete(
        @PathVariable("id")
        Long id) {
        cityItemViewService.delete(id);
    }

}
