package com.anastasko.lnucompass.web.controller.done;

import java.util.List;

import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.anastasko.lnucompass.infrastructure.UrlResourceViewService;
import com.anastasko.lnucompass.model.view.LongIdsList;
import com.anastasko.lnucompass.model.view.UrlResourceViewModel;

@Api(name = "UrlResource", description = "UrlResource")
@RestController
@RequestMapping("/urlResource")
public class UrlResourceController {

    @Autowired
    private UrlResourceViewService urlResourceViewService;

    @RequestMapping(method = RequestMethod.POST, value = "/findMany")
    public List<UrlResourceViewModel> findMany(
        @RequestBody
        LongIdsList ids) {
        return urlResourceViewService.findMany(ids.getIds());
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String upload(@RequestParam("file") MultipartFile file) {
    	return "done";
    }
    

}

