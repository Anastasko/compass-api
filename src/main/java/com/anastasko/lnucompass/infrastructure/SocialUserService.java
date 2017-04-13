package com.anastasko.lnucompass.infrastructure;

import com.anastasko.lnucompass.model.domain.SocialUserAccount;
import com.anastasko.lnucompass.model.enums.SocialProvider;
import com.anastasko.lnucompass.model.view.AuthViewModel;

public interface SocialUserService extends EntityPersistenceService<SocialUserAccount>{

	SocialUserAccount findByUserIdAndProvider(String userId, SocialProvider provider);

    SocialUserAccount findByAuth(AuthViewModel auth);
}
