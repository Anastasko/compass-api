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

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private SocialUserService socialUserService;

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
        SocialUserAccount acc = socialUserService.findByUserId(id);
        if (acc == null){
        	acc = new SocialUserAccount();
        	acc.setUserId(id);
        	String token = encoder.encode(auth.getToken());
        	Date exp = new Date(System.currentTimeMillis() + 1000*60*60*240);
        	acc.setName(name);
        	acc.setToken(token);
        	acc.setExpiration(exp);
        	socialUserService.create(acc);
        }     
        SocialUserViewModel userViewModel = new SocialUserViewModel(acc);
        userViewModel.setProvider(SocialProvider.FACEBOOK);
        return userViewModel;
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
        Boolean errors = resp.get("errors") != null && resp.get("errors").isArray() && resp.get("errors").size() > 0;
        if (errors){
            throw new SocialAuthenticationException("token isn't valid.");
        }
        SocialUserViewModel userViewModel = new SocialUserViewModel(null);
        userViewModel.setProvider(SocialProvider.TWITTER);
        userViewModel.setToken(encoder.encode(auth.getToken()));
        return userViewModel;
    }

    @RequestMapping(value = "/google_plus", method = RequestMethod.POST)
    public SocialUserViewModel google_plus(@RequestBody AuthViewModel auth) {

        URIBuilder builder = URIBuilder.fromUri(String.format("%s/oauth2/v1/tokeninfo", "https://www.googleapis.com"));
        builder.queryParam("access_token", auth.getToken());
        URI uri = builder.build();
        JsonNode resp = null;
        try {
            resp = restTemplate.getForObject(uri, JsonNode.class);
        } catch (HttpClientErrorException e) {
            throw new SocialAuthenticationException("Error validating token");
        }
        Boolean errors = resp.get("error") != null && !resp.get("error").isNull();
        if (errors){
            throw new SocialAuthenticationException("token isn't valid.");
        }
        SocialUserViewModel userViewModel = new SocialUserViewModel(null);
        userViewModel.setProvider(SocialProvider.GOOGLE_PLUS);
        userViewModel.setToken(encoder.encode(auth.getToken()));
        return userViewModel;
    }

}
