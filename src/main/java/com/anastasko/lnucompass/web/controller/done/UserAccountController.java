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

//	@Value("${facebook.app.id}")
//	private String facebookApp;
//	
//	@Value("${facebook.app.secret}")
//	private String facebookSecret;
//	
//	@Value("${endpoint}")
//	private String endpoint;
//	
//	private String requestURI;
//	
//	@Autowired
//	private RestTemplate restTemplate;
//	
//	@RequestMapping(value="/facebook", method=RequestMethod.GET)
//	public RedirectView signin(HttpServletRequest request){
//		requestURI = request.getRequestURI();
//		System.out.println(requestURI);
//		String redir = endpoint + "/auth/facebook/connect";
//		return new RedirectView("https://www.facebook.com/v2.8/dialog/oauth?client_id=" 
//		+ facebookApp 
//		+ "&redirect_uri=" + redir);
//	}
//	
//	@RequestMapping(value="/facebook/connect", method=RequestMethod.GET)
//	public @ResponseBody JsonNode connect(HttpServletRequest request){
//				
//		String code = request.getParameter("code");
//		
//		URIBuilder builder = URIBuilder.fromUri(String.format("%s/v2.8/oauth/access_token", "https://graph.facebook.com"));
//		builder.queryParam("client_id", facebookApp);
//		builder.queryParam("redirect_uri", endpoint + "/auth/facebook/connect");
//		builder.queryParam("client_secret", facebookSecret);
//		builder.queryParam("code", code);
//		URI uri = builder.build();
//
//		JsonNode resp = null;
//		try {
//			resp = restTemplate.getForObject(uri, JsonNode.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resp;
//	}
	
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
