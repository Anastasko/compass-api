package com.anastasko.lnucompass.web.controller.done;

import com.anastasko.lnucompass.infrastructure.AuthService;
import com.anastasko.lnucompass.model.view.AuthViewModel;
import com.anastasko.lnucompass.model.view.SocialUserViewModel;
import com.anastasko.lnucompass.model.view.TwitterAuthViewModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /* login */

    @PostMapping(value = "/auth/facebook")
    public SocialUserViewModel facebook(@RequestBody AuthViewModel auth) {
        return authService.authFacebook(auth);
    }

    @PostMapping(value = "/auth/twitter")
    public SocialUserViewModel twitter(@RequestBody TwitterAuthViewModel auth) {
        return authService.authTwitter(auth);
    }

    @PostMapping(value = "/auth/google")
    public SocialUserViewModel google(@RequestBody AuthViewModel auth) {
        return authService.authGoogle(auth);
    }

    /* logout */

    @PostMapping(value = "/logout")
    public void logout(@RequestBody AuthViewModel auth) {
        authService.logout(auth);
    }

}
