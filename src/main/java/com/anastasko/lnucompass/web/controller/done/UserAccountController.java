package com.anastasko.lnucompass.web.controller.done;

import org.jsondoc.core.annotation.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anastasko.lnucompass.infrastructure.UserService;
import com.anastasko.lnucompass.model.domain.UserAccount;
import com.anastasko.lnucompass.model.view.UserViewModel;
import com.anastasko.lnucompass.validation.exceptions.ServiceException;
import com.anastasko.lnucompass.web.controller.AbstractController;

@Api(name="User", description = "User")
@RequestMapping(value="/user")
@Controller
public class UserAccountController extends AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAccountController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody void signup(@RequestBody UserViewModel userModel){
		UserAccount user = new UserAccount();
		user.setUsername(userModel.getUsername());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		user.setEmail(userModel.getEmail());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setToken(user.getPassword());
		userService.create(user);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody String login(@RequestBody UserViewModel userModel){
		
		UserAccount user = userService.findByUsername(userModel.getUsername());
		if (user == null){
			throw new ServiceException("user with username '" + userModel.getUsername() + "' does not exist");
		}
		if (!passwordEncoder.matches(userModel.getPassword(), user.getPassword())){
			throw new ServiceException("password does not match");
		}
		return user.getToken();		
	}
				
		
}
