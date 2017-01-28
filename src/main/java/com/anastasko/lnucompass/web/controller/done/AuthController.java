package com.anastasko.lnucompass.web.controller.done;

import com.anastasko.lnucompass.infrastructure.AuthService;
import com.anastasko.lnucompass.model.enums.SocialProvider;
import com.anastasko.lnucompass.model.view.AuthViewModel;
import com.anastasko.lnucompass.model.view.SocialUserViewModel;
import org.jsondoc.core.annotation.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(name = "Auth", description = "Auth controller")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    /* login */

    @RequestMapping(value = "/auth/facebook", method = RequestMethod.POST)
    public SocialUserViewModel facebook(@RequestBody AuthViewModel auth) {
        return authService.authFacebook(auth);
    }

    @RequestMapping(value = "/auth/twitter", method = RequestMethod.POST)
    public SocialUserViewModel twitter(@RequestBody AuthViewModel auth) {
        return authService.authTwitter(auth);
    }

    @RequestMapping(value = "/auth/google", method = RequestMethod.POST)
    public SocialUserViewModel google(@RequestBody AuthViewModel auth) {
        return authService.authGoogle(auth);
    }

    /* logout */

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(@RequestBody AuthViewModel auth) {
        authService.logout(auth);
    }

}
