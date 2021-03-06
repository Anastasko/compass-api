package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.enums.SocialProvider;
import com.anastasko.lnucompass.model.view.AuthViewModel;
import com.anastasko.lnucompass.model.view.SocialUserViewModel;
import com.anastasko.lnucompass.model.view.TwitterAuthViewModel;

import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    SocialUserViewModel authGoogle(AuthViewModel auth);

    SocialUserViewModel authFacebook(AuthViewModel auth);

    SocialUserViewModel authTwitter(TwitterAuthViewModel auth);

    void logout(AuthViewModel auth);

}
