package com.anastasko.lnucompass.web.controller;

import org.jsondoc.springmvc.controller.JSONDocController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private JSONDocController documentationController;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String apiPage (Model model) {
		logger.info("get api docs");
	    model.addAttribute("jsondoc", documentationController.getApi());
	    return "jsondoc-ui";
	}

}
