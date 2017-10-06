package com.anastasko.lnucompass.web.controller;

import com.anastasko.lnucompass.infrastructure.UserService;
import com.anastasko.lnucompass.model.domain.UserAccount;
import com.anastasko.lnucompass.model.view.UserAuthViewModel;
import com.anastasko.lnucompass.model.view.UserViewModel;
import com.anastasko.lnucompass.validation.exceptions.ResourceNotFoundException;
import com.anastasko.lnucompass.web.controller.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value="/user")
@Controller
public class UserAccountController extends AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

	@Autowired
	private UserService userService;
	
	@PostMapping
	public @ResponseBody void signup(@RequestBody UserViewModel userModel){
		userService.registerNewUser(userModel);
	}

	@PostMapping(value="/auth")
	public @ResponseBody UserViewModel login(@RequestBody UserAuthViewModel userModel){
		return userService.auth(userModel);
	}
	
	@DeleteMapping
	public @ResponseBody void remove(@RequestBody UserAuthViewModel userModel){
		userService.auth(userModel);
		UserAccount user = userService.findByUsername(userModel.getUsername());
		if (user == null){
			throw new ResourceNotFoundException();
		}
		userService.deleteOne(user);
	}
		
}
