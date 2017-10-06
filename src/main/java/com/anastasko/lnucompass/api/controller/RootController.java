
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.anastasko.lnucompass.api.infrastructure.AndroidIconViewService;
import com.anastasko.lnucompass.api.infrastructure.CityItemViewService;
import com.anastasko.lnucompass.api.infrastructure.FacultyViewService;
import com.anastasko.lnucompass.api.infrastructure.IosIconViewService;
import com.anastasko.lnucompass.api.infrastructure.ItemKindViewService;
import com.anastasko.lnucompass.api.infrastructure.MapItemViewService;
import com.anastasko.lnucompass.api.infrastructure.MapViewService;
import com.anastasko.lnucompass.api.infrastructure.RootViewService;
import com.anastasko.lnucompass.api.model.view.EntityRootViewModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Root")
@RestController
@RequestMapping("/root")
public class RootController {

    @Autowired
    private RootViewService rootViewService;
    @Autowired
    private ItemKindViewService itemKindViewService;
    @Autowired
    private IosIconViewService iosIconViewService;
    @Autowired
    private AndroidIconViewService androidIconViewService;
    @Autowired
    private CityItemViewService cityItemViewService;
    @Autowired
    private MapViewService mapViewService;
    @Autowired
    private MapItemViewService mapItemViewService;
    @Autowired
    private FacultyViewService facultyViewService;

    @GetMapping
    public List<EntityRootViewModel> findAll() {
        return rootViewService.findAll();
    }

    @PostMapping
    public Long create(
        @RequestBody
        EntityRootViewModel item) {
        return rootViewService.create(item);
    }

    @GetMapping("/count")
    public Map<String, Integer> countAll() {
        Map<String, Integer> map = new TreeMap<String, Integer>();
        map.put("ItemKind", itemKindViewService.findAll().size());
        map.put("IosIcon", iosIconViewService.findAll().size());
        map.put("AndroidIcon", androidIconViewService.findAll().size());
        map.put("Root", rootViewService.findAll().size());
        map.put("CityItem", cityItemViewService.findAll().size());
        map.put("Map", mapViewService.findAll().size());
        map.put("MapItem", mapItemViewService.findAll().size());
        map.put("Faculty", facultyViewService.findAll().size());
        return map;
    }

}
