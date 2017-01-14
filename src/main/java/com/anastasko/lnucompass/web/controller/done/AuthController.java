package com.anastasko.lnucompass.web.controller.done;

import com.anastasko.lnucompass.model.enums.SocialProvider;
import com.anastasko.lnucompass.model.view.AuthViewModel;
import com.anastasko.lnucompass.model.view.SocialUserViewModel;
import com.fasterxml.jackson.databind.JsonNode;
import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialAuthenticationException;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Api(name = "Auth", description = "Auth controller")
@RequestMapping(value = "/auth")
@RestController
public class AuthController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder encoder;

    @RequestMapping(value = "/facebook", method = RequestMethod.POST)
    public SocialUserViewModel facebook(@RequestBody AuthViewModel auth) {
        URIBuilder builder = URIBuilder.fromUri(String.format("%s/debug_token", "https://graph.facebook.com"));
        builder.queryParam("input_token", auth.getToken());
        URI uri = builder.build();
        JsonNode resp = null;
        try {
            resp = restTemplate.getForObject(uri, JsonNode.class);
        } catch (HttpClientErrorException e) {
            throw new SocialAuthenticationException("Error validating token");
        }
        Boolean isValid = resp.path("data").findValue("is_valid").asBoolean();
        if (!isValid){
            throw new SocialAuthenticationException("token isn't valid");
        }
        SocialUserViewModel userViewModel = new SocialUserViewModel();
        userViewModel.setProvider(SocialProvider.FACEBOOK);
        userViewModel.setToken(encoder.encode(auth.getToken()));
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
        SocialUserViewModel userViewModel = new SocialUserViewModel();
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
        SocialUserViewModel userViewModel = new SocialUserViewModel();
        userViewModel.setProvider(SocialProvider.GOOGLE_PLUS);
        userViewModel.setToken(encoder.encode(auth.getToken()));
        return userViewModel;
    }

}
