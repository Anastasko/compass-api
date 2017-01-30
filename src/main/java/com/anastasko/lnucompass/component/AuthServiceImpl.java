package com.anastasko.lnucompass.component;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialAuthenticationException;
import org.springframework.social.support.URIBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.anastasko.lnucompass.infrastructure.AuthService;
import com.anastasko.lnucompass.infrastructure.SocialUserService;
import com.anastasko.lnucompass.model.domain.SocialUserAccount;
import com.anastasko.lnucompass.model.enums.SocialProvider;
import com.anastasko.lnucompass.model.view.AuthViewModel;
import com.anastasko.lnucompass.model.view.SocialUserViewModel;
import com.anastasko.lnucompass.model.view.TwitterAuthViewModel;
import com.anastasko.lnucompass.validation.exceptions.ServiceException;
import com.fasterxml.jackson.databind.JsonNode;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SocialUserService socialUserService;

    private void mergeFields(SocialUserAccount acc, SocialUserViewModel user) {
        acc.setFirstName(user.getFirstName());
        acc.setLastName(user.getLastName());
        acc.setImageUrl(user.getImageUrl());
    }

    private SocialUserAccount findOrCreateSocialUser(String id, String deviceUUID, SocialUserViewModel user) {
        SocialUserAccount acc = socialUserService.findByUserIdAndProvider(id, user.getProvider());
        if (acc == null) {
            acc = new SocialUserAccount();
            acc.setUserId(id);
            acc.setProvider(user.getProvider());
            mergeFields(acc, user);
            socialUserService.create(acc);
        } else {
            mergeFields(acc, user);
        }
        acc.setLogin(deviceUUID, acc.getId() + "-" + encoder.encode(user.getToken()));
        socialUserService.update(acc);
        return acc;
    }

    @Override
    @Transactional
    public SocialUserViewModel authGoogle(AuthViewModel auth) {
        checkAuth(auth);
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
        SocialUserViewModel view = new SocialUserViewModel(SocialProvider.GOOGLE);
        view.setToken(auth.getToken());
        view.setFirstName(resp.get("name").get("givenName").asText());
        view.setLastName(resp.get("name").get("familyName").asText());
        try {
            view.setImageUrl(resp.get("image").get("url").asText());
        } catch (Exception e) {
        }
        SocialUserAccount acc = findOrCreateSocialUser(id, auth.getDeviceUUID(), view);
        return new SocialUserViewModel(acc, auth.getDeviceUUID());
    }

    @Override
    @Transactional
    public SocialUserViewModel authFacebook(AuthViewModel auth) {
        checkAuth(auth);
        URIBuilder builder = URIBuilder.fromUri(String.format("%s/me", "https://graph.facebook.com"));
        builder.queryParam("fields", "id,last_name,first_name,picture");
        builder.queryParam("access_token", auth.getToken());
        URI uri = builder.build();
        JsonNode resp = null;
        try {
            resp = restTemplate.getForObject(uri, JsonNode.class);
        } catch (HttpClientErrorException e) {
            throw new SocialAuthenticationException("Error validating token");
        }
        SocialUserViewModel view = new SocialUserViewModel(SocialProvider.FACEBOOK);
        String id = resp.get("id").asText();
        view.setToken(auth.getToken());
        view.setFirstName(resp.get("first_name").asText());
        view.setLastName(resp.get("last_name").asText());
        try {
            view.setImageUrl(resp.get("picture").get("data").get("url").asText());
        } catch (Exception e) {
        }
        SocialUserAccount acc = findOrCreateSocialUser(id, auth.getDeviceUUID(), view);
        return new SocialUserViewModel(acc, auth.getDeviceUUID());
    }

    @Override
    @Transactional
    public SocialUserViewModel authTwitter(TwitterAuthViewModel auth) {
    	checkAuth(auth);
        if (auth.getTokenSecret() == null || auth.getTokenSecret().length()<1){
        	throw new ServiceException("twitter auth must contain token secret");
        }
        TwitterFactory factory = new TwitterFactory();
        AccessToken accessToken = new AccessToken(auth.getToken(), auth.getTokenSecret());
        Twitter twitter = factory.getInstance();
        twitter.setOAuthConsumer("036iLbempT7VznCfIDOVblcBJ", "gQJKStNOgNTsrxzxnZDkjccUWPe2fhuRk4jCZQsmmn07egIrrL");
        twitter.setOAuthAccessToken(accessToken);
        String id = null;
        SocialUserViewModel view = new SocialUserViewModel(SocialProvider.TWITTER);
        try {
			User user = twitter.verifyCredentials();
			id = ""+user.getId();
			view.setToken(auth.getToken());
			try {
	        view.setFirstName(user.getName().split(" ")[0]);
	        view.setLastName(user.getName().split(" ")[1]);
			} catch (Exception e) {}
	        view.setImageUrl(user.getProfileImageURLHttps());
	       
		} catch (TwitterException e) {
			throw new ServiceException(e.getMessage());
		}
        SocialUserAccount acc = findOrCreateSocialUser(id, auth.getDeviceUUID(), view);
        return new SocialUserViewModel(acc, auth.getDeviceUUID());
    }

    private void checkAuth(AuthViewModel auth) {
        if (auth.getToken() == null){
            throw new ServiceException("auth must contain token");
        }
        if (auth.getToken().length()==0){
            throw new ServiceException("token must not be empty string");
        }
        if (auth.getDeviceUUID() == null){
            throw new ServiceException("auth must contain deviceUUID");
        }
        if (auth.getDeviceUUID().length()==0){
            throw new ServiceException("deviceUUID must not be empty string");
        }
    }

    @Override
    @Transactional
    public void logout(AuthViewModel auth) {
        checkAuth(auth);
        SocialUserAccount acc = socialUserService.findByAuth(auth);
        if (acc == null){
            throw new ServiceException("can't logout. reason: no such login");
        } else {
            acc.getLogins().remove(auth.getDeviceUUID());
            socialUserService.update(acc);
        }
    }

}
