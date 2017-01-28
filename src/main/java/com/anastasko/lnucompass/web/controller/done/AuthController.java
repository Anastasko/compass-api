package com.anastasko.lnucompass.web.controller.done;

import java.net.URI;
import java.time.Instant;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialAuthenticationException;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.anastasko.lnucompass.infrastructure.PropertyService;
import com.anastasko.lnucompass.infrastructure.SocialUserService;
import com.anastasko.lnucompass.model.domain.SocialUserAccount;
import com.anastasko.lnucompass.model.enums.SocialProvider;
import com.anastasko.lnucompass.model.view.AuthViewModel;
import com.anastasko.lnucompass.model.view.SocialUserViewModel;
import com.fasterxml.jackson.databind.JsonNode;

@Api(name = "Auth", description = "Auth controller")
@RequestMapping(value = "/auth")
@RestController
public class AuthController {

	private static long TOKEN_EXPIRATION_TIME = 1000*60*60*240; // 10 days
	
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private SocialUserService socialUserService;
  
    private SocialUserAccount findOrCreateSocialUser(String id, String name, String token, SocialProvider provider) {
    	SocialUserAccount acc = socialUserService.findByUserIdAndProvider(id, provider);
        Date exp = new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME);
        if (acc == null){
        	acc = new SocialUserAccount();
        	acc.setUserId(id);
        	acc.setProvider(provider);
        	acc.setName(name);
        	acc.setToken(token);
        	acc.setExpiration(exp);
        	socialUserService.create(acc);
        } else {
        	acc.setName(name);
        	acc.setToken(token);
        	acc.setExpiration(exp);
        	socialUserService.update(acc);
        }
        return acc;
	}
    
    @RequestMapping(value = "/facebook", method = RequestMethod.POST)
    public SocialUserViewModel facebook(@RequestBody AuthViewModel auth) {
        URIBuilder builder = URIBuilder.fromUri(String.format("%s/me", "https://graph.facebook.com"));
        builder.queryParam("access_token", auth.getToken());
        URI uri = builder.build();
        JsonNode resp = null;
        try {
            resp = restTemplate.getForObject(uri, JsonNode.class);
        } catch (HttpClientErrorException e) {
            throw new SocialAuthenticationException("Error validating token");
        }
        String id = resp.get("id").asText();   
        String name = resp.get("name").asText();
        String token = encoder.encode(auth.getToken());
        SocialUserAccount acc = findOrCreateSocialUser(id, name, token, SocialProvider.FACEBOOK);
        return new SocialUserViewModel(acc);
    }

	@RequestMapping(value = "/twitter", method = RequestMethod.POST)
    public SocialUserViewModel twitter(@RequestBody AuthViewModel auth) {

        URIBuilder builder = URIBuilder.fromUri(String.format("%s/1.1/account/verify_credentials.json", "https://api.twitter.com"));
        builder.queryParam("oauth_access_token", auth.getToken());
        URI uri = builder.build();
        JsonNode resp = null;
        try {
            resp = restTemplate.getForObject(uri, JsonNode.class);
        } catch (HttpClientErrorException e) {
            throw new SocialAuthenticationException("Error validating token");
        }
        String id = resp.get("id_str").asText();
        String name = resp.get("name").asText();
        String token = encoder.encode(auth.getToken());
        SocialUserAccount acc = findOrCreateSocialUser(id, name, token, SocialProvider.GOOGLE);
        return new SocialUserViewModel(acc);
    }

    @RequestMapping(value = "/google", method = RequestMethod.POST)
    public SocialUserViewModel google_plus(@RequestBody AuthViewModel auth) {

        URIBuilder builder = URIBuilder.fromUri(String.format("%s/plus/v1/people/me", "https://www.googleapis.com"));
        builder.queryParam("access_token", auth.getToken());
        URI uri = builder.build();
        JsonNode resp = null;
        try {
            resp = restTemplate.getForObject(uri, JsonNode.class);
        } catch (HttpClientErrorException e) {
            throw new SocialAuthenticationException("Error validating token");
        }
        String id = resp.get("id").asText();
        String name = resp.get("displayName").asText();
        String token = encoder.encode(auth.getToken());
        SocialUserAccount acc = findOrCreateSocialUser(id, name, token, SocialProvider.GOOGLE);
        return new SocialUserViewModel(acc);
    }

}
