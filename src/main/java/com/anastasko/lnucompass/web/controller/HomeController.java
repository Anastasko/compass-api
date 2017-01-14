package com.anastasko.lnucompass.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.pojo.ApiVisibility;
import org.jsondoc.springmvc.controller.JSONDocController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anastasko.lnucompass.infrastructure.ListIconsService;
import com.anastasko.lnucompass.infrastructure.UserService;

//@Api(name="HomeController", description="HomeController", visibility=ApiVisibility.PRIVATE)
@Controller
public class HomeController extends AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	ListIconsService listIconsService;
	
//	@Autowired
//	MapService entityMapService;
//
//	@Autowired
//	CityItemService entityBuildingService;
	
	@Autowired
	private UserService userService;
		
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
	@Autowired
	private JSONDocController documentationController;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String apiPage (Model model) {
	    model.addAttribute("jsondoc", documentationController.getApi());
	    return "jsondoc-ui";
	}
	

	
}
