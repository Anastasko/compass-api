package com.anastasko.lnucompass.web.controller.done;

import java.util.Map;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anastasko.lnucompass.infrastructure.ListIconsService;
import com.anastasko.lnucompass.model.enums.ItemKind;

@Api(name="Icons", description = "Retrieve icon's name & url", visibility=ApiVisibility.PRIVATE)
@RestController
@RequestMapping("/icons")
public class ListIconsController  {

	@Autowired
	private ListIconsService listIconsService;
	
	@RequestMapping(value="/map", method=RequestMethod.GET)
	public Map<ItemKind, String> getMap(){
		return listIconsService.getMap();
	}
	
	@RequestMapping(value="/ios", method=RequestMethod.GET)
    public Map<String, Map<String, String>> getiosIcons(){
		return listIconsService.getiosIcons();
	}
	
	@RequestMapping(value="/android", method=RequestMethod.GET)
    public Map<String, Map<String, String>> getAndroidIcons(){
		return listIconsService.getAndroidIcons();
	}
}
