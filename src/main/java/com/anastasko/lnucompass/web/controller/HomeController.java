package com.anastasko.lnucompass.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@GetMapping
	public String apiPage (Model model) {
		logger.info("get api docs");
	    return "redoc-ui";
	}

}
