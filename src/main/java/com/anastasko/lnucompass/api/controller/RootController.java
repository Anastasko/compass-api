
package com.anastasko.lnucompass.api.controller;

import java.util.List;
import com.anastasko.lnucompass.api.infrastructure.RootViewService;
import com.anastasko.lnucompass.api.model.view.EntityRootViewModel;
import com.anastasko.lnucompass.model.view.LongIdsList;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(name = "Root", description = "Root")
@RestController
@RequestMapping("/root")
public class RootController {

    @Autowired
    private RootViewService rootViewService;

    @RequestMapping(method = RequestMethod.GET)
    public List<EntityRootViewModel> findAll() {
        return rootViewService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public List<ObjectNode> forSynchronisation(
        @RequestBody
        LongIdsList ids) {
        return rootViewService.findForSynchronisation(ids.getIds());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long create(
        @RequestBody
        EntityRootViewModel item) {
        return rootViewService.create(item);
    }

}
